import java.util.*;
public class calculator {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		
		String line1 = spacecanceller(console.nextLine()); //the input from the user is edited as is it is entered
		String line2 = spacecanceller(console.nextLine()); //the input from the user is edited as is it is entered
		String line3 = spacecanceller(console.nextLine()); //the input from the user is edited as is it is entered
		String variable1 = (variabledetector(line1)); //first inputs variable is stored	
		String value1 = returner(line1); //first inputs value part is stored
		value1 = addingpointzerotodouble(line1, value1); 
		String variable2 = (variabledetector(line2)); //second inputs variable is stored
		String value2 = returner(line2); //second inputs value part is stored
		value2 = addingpointzerotodouble(line2, value2);
		String variable3 = (variabledetector(line3)); //third inputs variable is stored
		String value3 = returner(line3); //third inputs value part is stored
		value3 = addingpointzerotodouble(line3, value3);
		
		String question = spacecanceller((console.nextLine())); //the input from the user is edited as is it is entered
		
		if(question.contains(variable1)) { //variable and value is replaced at the question string
			question = question.replace(variable1, (value1)); 
		}
		
		if(question.contains(variable2)) { //variable and value is replaced at the question string
			question = question.replace(variable2, (value2)); 
		}
		 
		if(question.contains(variable3)) { //variable and value is replaced at the question string
			question = question.replace(variable3, (value3)); 
		}
		
		String result = parenthesis(question);
		System.out.println(result.substring(0, result.length()-1)); //The reason why I used -1 is to eliminate semicolon at the end of the string named result. 
	}
		
		
	public static String spacecanceller (String text) { //This method erase the whitespaces of its parameter.
		String zero = ""; //zero string stands for text string without spaces.
		for(int i=0; i<text.length(); i++) { //int i is the index of the characters which will be examined.
			if(text.charAt(i) != ' ') { //if there is no whitespace at that character, we are adding it to a string which I formed at the beginning of the method.
				zero += text.charAt(i);
			}
		}
		return zero;
	}
	
	public static String variabledetector (String text) { //This method gets the variable name from the input. For example: (inty=9); y is obtained.
		String variable; //stands for name of the variable
		if(text.contains("double")) { //and we determine the variable name in two different ways if the type is double or int.
			variable = text.substring(6,text.indexOf("="));
		}else {
			variable = text.substring(3,text.indexOf("="));
		}
		return variable;
	}
	
	public static String returner (String text) { //This method gets the variable value from the input. For example: (inty=9); 9 is obtained.
		return (text.substring(text.indexOf('=')+1, text.length()-1));
	}
	
	public static String addingpointzerotodouble (String line, String value) { //This method converts the number to a double type if it is entered as integer but wanted to use as double.
		if(line.contains("double") && !line.contains(".")) {
			value += ".0";
			return value;
		}else {
			return value;
		}
	}
	
	public static String summation (String tex1, String tex2) { //This method does the summation operation with its parameters.
		double x1 = 0; //x1 will be our variable if tex1 is double
		int y1 = 0; //y1 will be our variable if tex1 is int
		double x2 = 0; //x2 will be our variable if tex1 is double
		int y2 = 0; //y2 will be our variable if tex1 is int
		String sum = ""; //created to store sum as a string
		
		if(tex1.contains(".")) { //if we have a dot at our first parameter, we know that it is a double type, so we must parse it as double. if not, we must parse it as int.
			x1 = Double.parseDouble(tex1);
		}else {
		    y1 = Integer.parseInt(tex1);
		}
		
		if(tex2.contains(".")) { //if we have a dot at our second parameter, we know that it is a double type, so we must parse it as double. if not, we must parse it as int.
			x2 = Double.parseDouble(tex2);
		}else {
			y2 = Integer.parseInt(tex2);
		}
		
		if(tex1.contains(".") && tex2.contains(".")) { //at this part I used several if else because we do not know what type our parameters are, and in which variable they stored. 
			sum = "" + (x1 + x2);
		}else if(tex1.contains(".") && !tex2.contains(".")) {
			sum = "" + (x1 + y2);
		}else if(!tex1.contains(".") && tex2.contains(".")) {
			sum = "" + (y1 + x2);
		}else {
			sum = "" + (y1 + y2);
		}
		
		return sum; //returned to the sum of parameters
	}
	
	public static String substraction (String tex1, String tex2) { //This method does the subtraction operation with its parameters.
		double x1 = 0; //x1 will be our variable if tex1 is double
		int y1 = 0; //y1 will be our variable if tex1 is int
		double x2 = 0; //x2 will be our variable if tex1 is double
		int y2 = 0; //y2 will be our variable if tex1 is int
		String minus = ""; //created to store subtraction as a string
		
		if(tex1.contains(".")) { //if we have a dot at our first parameter, we know that it is a double type, so we must parse it as double. if not, we must parse it as int.
			x1 = Double.parseDouble(tex1);
		}else {
		    y1 = Integer.parseInt(tex1);
		}
		
		if(tex2.contains(".")) { //if we have a dot at our second parameter, we know that it is a double type, so we must parse it as double. if not, we must parse it as int.
			x2 = Double.parseDouble(tex2);
		}else {
			y2 = Integer.parseInt(tex2);
		}
		
		if(tex1.contains(".") && tex2.contains(".")) { //at this part I used several if else because we do not know what type our parameters are, and in which variable they stored.
			minus = "" + (x1 - x2);
		}else if(tex1.contains(".") && !tex2.contains(".")) {
			minus = "" + (x1 - y2);
		}else if(!tex1.contains(".") && tex2.contains(".")) {
			minus = "" + (y1 - x2);
		}else {
			minus = "" + (y1 - y2);
		}
		
		return minus; //returned to the subtraction of parameters
	}
	
	public static String multiplication (String tex1, String tex2) { //This method does the multiplication operation with its parameters.
		double x1 = 0; //x1 will be our variable if tex1 is double
		int y1 = 0; //y1 will be our variable if tex1 is int
		double x2 = 0; //x2 will be our variable if tex1 is double
		int y2 = 0; //y2 will be our variable if tex1 is int
		String sum = ""; //created to store prodcut as a string
		
		if(tex1.contains(".")) { //if we have a dot at our first parameter, we know that it is a double type, so we must parse it as double. if not, we must parse it as int.
			x1 = Double.parseDouble(tex1);
		}else {
		    y1 = Integer.parseInt(tex1);
		}
		
		if(tex2.contains(".")) { //if we have a dot at our second parameter, we know that it is a double type, so we must parse it as double. if not, we must parse it as int.
			x2 = Double.parseDouble(tex2);
		}else {
			y2 = Integer.parseInt(tex2);
		}
		
		if(tex1.contains(".") && tex2.contains(".")) { //at this part I used several if else because we do not know what type our parameters are, and in which variable they stored.
			sum = "" + (x1 * x2);
		}else if(tex1.contains(".") && !tex2.contains(".")) {
			sum = "" + (x1 * y2);
		}else if(!tex1.contains(".") && tex2.contains(".")) {
			sum = "" + (y1 * x2);
		}else {
			sum = "" + (y1 * y2);
		}
		
		return sum; //returned to the multiplication of parameters
	}
	
	public static String division (String tex1, String tex2) { //This method does the division operation with its parameters.
		double x1 = 0; //x1 will be our variable if tex1 is double
		int y1 = 0; //y1 will be our variable if tex1 is int
		double x2 = 0; //x2 will be our variable if tex1 is double
		int y2 = 0; //y2 will be our variable if tex1 is int
		String sum = ""; //created to store quotient as a string
		
		if(tex1.contains(".")) { //if we have a dot at our first parameter, we know that it is a double type, so we must parse it as double. if not, we must parse it as int.
			x1 = Double.parseDouble(tex1);
		}else {
		    y1 = Integer.parseInt(tex1);
		}
		
		if(tex2.contains(".")) { //if we have a dot at our second parameter, we know that it is a double type, so we must parse it as double. if not, we must parse it as int.
			x2 = Double.parseDouble(tex2);
		}else {
			y2 = Integer.parseInt(tex2);
		}
		
		if(tex1.contains(".") && tex2.contains(".")) { //at this part I used several if else because we do not know what type our parameters are, and in which variable they stored.
			sum = "" + (x1 / x2);
		}else if(tex1.contains(".") && !tex2.contains(".")) {
			sum = "" + (x1 / y2);
		}else if(!tex1.contains(".") && tex2.contains(".")) {
			sum = "" + (y1 / x2);
		}else {
			sum = "" + (y1 / y2);
		}
		
		return sum; //returned to the division of parameters
		
	}
	
	public static String parenthesis (String question) { //This method finds the parenthesis and sends the string in it, to a calculator method.
		String inside = ""; //inside string represents between two parenthesis.
		String s = question;
		while(question.contains("(")) { //since I want to wipe out all the parenthesis, I used while.
			for(int i = question.length()-1; i >= 0; ) { //int i is the index of characters which will be examined.
			if(question.charAt(i) == '(') { //if we capture a lefthanded parenthesis, we start to look for a righthanded one to send the what is between them.
				for(int j = i+1; j < question.length()-1; j++) { //int j is the index of characters which will be calculated in the calculator method.
					if(question.charAt(j) == ')') {
						break;
					}
					inside += question.charAt(j);
					
					}
				}
			question = question.replace("("+ inside + ")", calculator(inside)); //at this point we are replacing parenthesis and what is between them with the result of calculator method.
			i--;
			inside = ""; //the reason why I formatted the string called inside, is to make sure that the other parenthesis work well after the first one.
			}
		}
		return calculator(question); //after all the parenthesis wiped out, we are returning our ultimate result, the output.
	}
	
	public static String calculator (String question) { //this method creates two parameters and sends them to other methods created above to do calculation.
		for(int i = 0; i < question.length(); i++) { //int i is the index of the character which will be examined.	
			if(question.charAt(i) == '*') { //* character tells us that parameters, which will be created below, is going to be sent to multiplication method.
				String operant1 = ""; //parameter1 for multiplication
				String operant2 = ""; //parameter2 for multiplication
				int k; //int k is the index of characters which is going to be our parameter
				for(k = i-1; k >= 0; k--) {
					if((question.charAt(k) >= '0' && question.charAt(k) <= '9') || question.charAt(k) == '.') { //we are adding the characters as long as we see a number or a dot.
						operant1 = question.charAt(k) + operant1;
					}else {
						break;
					}
				}
				
				int l; //int l is the index of characters which is going to be our parameter
				for(l = i+1; l < question.length(); l++) {
					if((question.charAt(l) >= '0' && question.charAt(l) <= '9') || question.charAt(l) == '.') { //we are adding the characters as long as we see a number or a dot.
						operant2 += question.charAt(l);
					}else {
						break;
					}
				}
				
				String product = multiplication(operant1, operant2); //parameters sent to multiplication method to create product string
				question = question.replace(question.substring(k+1, l), (product)); //after the calculation, we are replacing operants and * symbol at the question string with the product.
				
				if(operant1 != "" && operant2 != "") { //if there is no string created for operant1 or operant2, i goes on, but if they both exist i is resetted.
					i = 0;
				}
			}
			
			if(question.charAt(i) == '/') { // / character tells us that parameters, which will be created below, is going to be sent to division method.
				String operant1 = ""; //parameter1 for division
				String operant2 = ""; //parameter2 for division
				int k; //int k is the index of characters which is going to be our parameter
				for(k = i-1; k >= 0; k--) { 
					if((question.charAt(k) >= '0' && question.charAt(k) <= '9') || question.charAt(k) == '.') { //we are adding the characters as long as we see a number or a dot.
						operant1 = question.charAt(k) + operant1;
					}else {
						break;
					}
				}
				
				int l; //int l is the index of characters which is going to be our parameter
				for(l = i+1; l < question.length(); l++) {
					if((question.charAt(l) >= '0' && question.charAt(l) <= '9') || question.charAt(l) == '.') { //we are adding the characters as long as we see a number or a dot.
						operant2 += question.charAt(l);
					}else {
						break;
					}
				}
				
				String tekz = division(operant1, operant2); //parameters sent to division method to create tekz string
				question = question.replace(question.substring(k+1, l), (tekz)); //after the calculation, we are replacing operants and / symbol at the question string with the tekz.
				
				if(operant1 != "" && operant2 != "") {  //if there is no string created for operant1 or operant2, i goes on, but if they both exist i is resetted.
					i = 0;
				}
			}
		}
		
		for(int i = 0; i < question.length(); i++) {
			if(question.charAt(i) == '+') { //+ character tells us that parameters, which will be created below, is going to be sent to summation method.
				String operant1 = ""; //parameter1 for summation
				String operant2 = ""; //parameter2 for summation
				int k; //int k is the index of characters which is going to be our parameter
				for(k = i-1; k >= 0; k--) {
					if((question.charAt(k) >= '0' && question.charAt(k) <= '9') || question.charAt(k) == '.') { //we are adding the characters as long as we see a number or a dot.
						operant1 = question.charAt(k) + operant1;
					}else {
						break;
					}
				}
				
				int l; //int l is the index of characters which is going to be our parameter
				for(l = i+1; l < question.length(); l++) {
					if((question.charAt(l) >= '0' && question.charAt(l) <= '9') || question.charAt(l) == '.') { //we are adding the characters as long as we see a number or a dot.
						operant2 += question.charAt(l); 
					}else {
						break;
					}
				}
				
				String sum = summation(operant1, operant2); //parameters sent to summation method to create sum string
				question = question.replace(question.substring(k+1, l), (sum)); //after the calculation, we are replacing operants and + symbol at the question string with the sum.
				
				if(operant1 != "" && operant2 != "") {  //if there is no string created for operant1 or operant2, i goes on, but if they both exist i is resetted.
					i = 0;
				}
			}
			
			if(question.charAt(i) == '-') { //- character tells us that parameters, which will be created below, is going to be sent to substraction method.
				String operant1 = ""; //parameter1 for subtraction
				String operant2 = ""; //parameter2 for subtraction
				int k; //int k is the index of characters which is going to be our parameter
				for(k = i-1; k >= 0; k--) {
					if((question.charAt(k) >= '0' && question.charAt(k) <= '9') || question.charAt(k) == '.') { //we are adding the characters as long as we see a number or a dot.
						operant1 = question.charAt(k) + operant1;
					}else {
						break;
					}
				}
				
				int l; //int l is the index of characters which is going to be our parameter
				for(l = i+1; l < question.length(); l++) {
					if((question.charAt(l) >= '0' && question.charAt(l) <= '9') || question.charAt(l) == '.') { //we are adding the characters as long as we see a number or a dot.
						operant2 += question.charAt(l);
					}else {
						break;
					}
				}
				
				String minus = substraction(operant1, operant2); //parameters sent to substraction method to create minus string
				question = question.replace(question.substring(k+1, l), (minus)); //after the calculation, we are replacing operants and - symbol at the question string with the minus.
				 
				if(operant1 != "" && operant2 != "") {  //if there is no string created for operant1 or operant2, i goes on, but if they both exist i is resetted.
					i = 0;
				}
			}
		}
		return question; //our parameter is returned with the edited version.
	}
}
