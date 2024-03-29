package pt.iscte.paddle.model.validation;

import java.util.List;

import pt.iscte.paddle.model.IProgramElement;

public interface ISemanticProblem {
	enum Type {
		INCOMPATIBLE_ASSIGNMENT,
		INCOMPATIBLE_RETURN,
		MISSING_RETURN;
	}
	
	String getMessage();
	List<IProgramElement> getProgramElements();
	
	
	static ISemanticProblem create(String message, IProgramElement ... elements) {
		return new SemanticProblem(message, elements);
	}
	
	
	class SemanticProblem implements ISemanticProblem {
		private final String message;
		private final List<IProgramElement> elements;
		
		private SemanticProblem(String message, IProgramElement ... elements) {
			this.message = message;
			this.elements = List.of(elements);
		}
		
		@Override
		public List<IProgramElement> getProgramElements() {
			return elements;
		}
		
		@Override
		public String getMessage() {
			return message;
		}
		
		@Override
		public String toString() {
			return message + " " + elements; 
		}
	}

}
