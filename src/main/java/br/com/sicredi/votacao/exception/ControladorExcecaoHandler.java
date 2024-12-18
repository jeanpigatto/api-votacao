package br.com.sicredi.votacao.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControladorExcecaoHandler {
	
	@Autowired
    private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDTO> handle(MethodArgumentNotValidException excecao) {
		
		ArrayList<ErroDeFormularioDTO> dto =  new ArrayList<>();
	    List<FieldError> fieldErrors = excecao.getBindingResult().getFieldErrors();
	    fieldErrors.forEach(e -> {
	            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
	            ErroDeFormularioDTO erro = new ErroDeFormularioDTO(e.getField(), mensagem); 
	            dto.add(erro);
	     });
	     return dto;
	 }		

	  @ExceptionHandler(value = {NaoEncontradoException.class})
	  public ResponseEntity<ErroGeralDTO> resourceNotFoundException(NaoEncontradoException ex) {
		  
		  ErroGeralDTO message = new ErroGeralDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		  return new ResponseEntity<ErroGeralDTO>(message, HttpStatus.NOT_FOUND);
	  }
	  
	  @ExceptionHandler(value = {BussinessException.class})
	  public ResponseEntity<ErroGeralDTO> bussinessException(BussinessException ex) {
		  
		  ErroGeralDTO message = new ErroGeralDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		  return new ResponseEntity<ErroGeralDTO>(message, HttpStatus.BAD_REQUEST);
	  }
}
