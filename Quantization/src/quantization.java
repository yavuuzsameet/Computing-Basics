import java.util.*;
import java.io.*;
public class quantization {

	public static void main(String[] args) throws FileNotFoundException {
		
		int mode = Integer.parseInt(args[0]); //mode decides which function of the program will run.
		String InputName = args[1]; //stores the file name which will be processed. 
		
		Scanner input = new Scanner(new File(InputName)); //scans the file to create a 3d-array.
		int[][][] array = create3dArray(input); //a three dimensional array is created with scanner as parameter.
		
		if(mode==0) reWrite(array, "output.ppm"); //constitute a new file with same old array.
		if(mode==1) reWrite(blackWhite(array), "black-and-white.ppm"); //constitute a new file after sending array to blackWhite method.
		if(mode==2) { 
			String FilterName = args[2]; //stores the filter name which will be used.
			Scanner filtertext = new Scanner(new File(FilterName)); //scans the filter file to create a 2d-array.
			int[][] array2 = create2dArray(filtertext); //a two dimensional array is created with scanner as parameter.
			reWrite(blackWhite(convolution(array, array2)), "convolution.ppm"); //constitute a new file after sending array and filter to convolution method which sent into blackWhite method.
		
		}
		if(mode==3) {
			int range = Integer.parseInt(args[2]); //stores the range, that is given in arguments, to use while checking neighbours. 
			boolean[][][] truefalse = new boolean[array.length][array.length][3]; //this 3d-boolean array controls whether the element of an array is changed.
			mein(array, truefalse, range); //drives quantize method.
		}
	}
	
	public static int[][] create2dArray(Scanner input) { //this method is formed to create two dimensional arrays by taking scanner (which scans filter/kernel) as parameter.
		String firstline = input.next(); //reads the first line of scanner which consist of size of the filter.
		int filtersize = Integer.parseInt(firstline.charAt(0)+""); //stores the size of filter.
		int[][] array = new int[filtersize][filtersize]; //a 2d-array is created.
		for(int i=0; i<filtersize; i++) { //i is the row
			for(int j=0; j<filtersize; j++) { //j is the column
				array[i][j] = input.nextInt(); //elements of the array is filled up.
			}
		}
		return array; //created array is returned.
		
	}
	
	public static int[][][] create3dArray(Scanner input) { //this method is formed to create three dimensional arrays by taking scanner (which scans input file) as parameter.
		String header = input.next(); //stores the header/type of ppm file which has no help of creating 3d-array.
		int column = input.nextInt(); //stores the number of columns. 
		int row = input.nextInt(); //stores the number of rows.
		int max = input.nextInt(); //stores the maximum value which an element of an array can get.
		int[][][] array = new int[row][column][3]; //a 3d-array is created by using number of rows, columns and color channels.
		for(int i=0; i<row; i++) { //i is the row.
			for(int j=0; j<column; j++) { //j is the column.
				for(int k=0; k<3; k++) { //k is color channel.
					array[i][j][k] = input.nextInt(); //elements of the array is filled up.
				}
			}
		}
		return array; //created array is returned.
		
	}
	
	public static void reWrite (int[][][] array, String filename) throws FileNotFoundException { //this method prints given array into a file whose name is given as parameter.
		PrintStream output = new PrintStream(new File(filename)); //output object is created.
		output.println("P3"); //first of all, the header of ppm file is printed to file. (we know that it is P3 always, for this assignment)
		output.println(array.length + " " + array[0].length); //the number of columns and rows are printed to the second line.
		output.println("255"); //maximum value of the elements of the ppm file can get is printed. (we know that it is 255 always, for this assignment) 
		for(int i=0; i<array.length; i++) { //i is the row.
			for(int j=0; j<array[i].length; j++) { //j is the column.
				for(int k=0; k<3; k++) { //k is color channel.
					output.print(array[i][j][k]+" "); //every single element of an array is printed out one by one.
				}
				output.print("\t"); //tab is used as long space after every triplet.
			}
			output.print("\n"); //new line is used to differentiate rows.
		}
	}
	
	public static int[][][] blackWhite (int[][][] array) throws FileNotFoundException { //this method takes an array and calculates the averages of the elements 
		for(int i=0; i<array.length; i++) { //i is the row.                             //which stands back to back in dimensional position.
			for(int j=0; j<array[i].length; j++) { //j is the column.                   //and then assigns the average value to all elements which has same row and column but different color dimension.    
				int sum = 0; 
				for(int k=0; k<3; k++) { //k is the color dimension.
					sum += array[i][j][k]; //the sum of the elements which has same row and column is evaluated. 
				}
				int avg = sum/3; //stores average.
				for(int k=0; k<3; k++) { //k is the color dimension.
					array[i][j][k] = avg; //the average is assigned to all elements which has same row and column.
				}
			}
		}
		return array; //the average calculated array is returned which is going to give black and white image after it is printed out to a file.
	}
	
	public static int[][][] convolution(int[][][] array3, int[][] array2){       //this method show 2d-array(filter) round on 3d-array(image) and multiply the overlapping elements of the arrays 
		int[][][] newarray = new int[array3.length-array2.length+1][array3[0].length-array2.length+1][3];              //and sums the products to assign the sum to a new array.            
		//the number of columns and rows decreases because the sum is assigned to the middle point of filter array.
		for(int k=0; k<3; k++) { //k is the color dimension of image.
		    for(int i=0; i<array3.length-array2.length+1; i++) { //i is the row of image.
		    	for(int j=0; j<array3.length-array2.length+1; j++) { //j is the column of image.
		    		int sum = 0;
		    		for(int f=0; f<array2.length; f++) { //f is the row of filter.
		    			for(int g=0; g<array2.length; g++) { //g is the column of filter.
		    				sum += array3[i+f][j+g][k] * array2[f][g]; //the overlapping elements is multiplied and summed at last.
		    				
						}
					}
		    		if(sum<0) sum=0; //sum becomes zero if the sum is less than zero.
		    		if(sum>255) sum=255; //sum becomes 255 if the sum is greater than 255.
		    		newarray[i][j][k] = sum; //the sum is assigned to the new array whose size is smaller than image array.
		    		
		    	}
			}
		    
		}
		return newarray; //convoluted array is returned which is going to give edited image after it is printed out to a file.
	}

	public static void quantize (int i, int j, int k, int[][][] array, boolean[][][] truefalse, int range) { //i, j, k represents positions of the elements of an array.
		//this method check the neighbours of an element of an array whether they are in range and if it decides that they are in, it changes the neighbour element to 'our element'. 
		//this way the number of colors in the photo decreases, so as quality.
		
		if(i+1<array.length) { //checks boundaries.
		if(Math.abs(array[i+1][j][k] - array[i][j][k]) <= range && truefalse[i+1][j][k] == false) { //checks if the difference is in the given range and the neighbour is changed before or not.
			array[i+1][j][k] = array [i][j][k]; //the neighbour element is equalized to 'our element'.
			truefalse[i+1][j][k] = true; //the neighbour element marked as changed.
			quantize(i+1, j, k, array, truefalse, range); //the neighbour element sent into method as parameter to be 'our element' in the next call of the method.
		}
		}
		if(i>0 ) { //checks boundaries.
		if(Math.abs(array[i-1][j][k] - array[i][j][k]) <= range && truefalse[i-1][j][k] == false) { //checks if the difference is in the given range and the neighbour is changed before or not.
			array[i-1][j][k] = array[i][j][k]; //the neighbour element is equalized to 'our element'.
			truefalse[i-1][j][k] = true; //the neighbour element marked as changed.
			quantize(i-1, j, k, array, truefalse, range); //the neighbour element sent into method as parameter to be 'our element' in the next call of the method.

		}
		}
		if(j+1<array.length ) { //checks boundaries.
		if(Math.abs(array[i][j+1][k] - array[i][j][k]) <= range && truefalse[i][j+1][k] == false) { //checks if the difference is in the given range and the neighbour is changed before or not.
			array[i][j+1][k] = array[i][j][k]; //the neighbour element is equalized to 'our element'.
			truefalse[i][j+1][k] = true; //the neighbour element marked as changed.
			quantize(i, j+1, k, array, truefalse, range); //the neighbour element sent into method as parameter to be 'our element' in the next call of the method.

		}
		}
		if(j>0) { //checks boundaries.
		if(Math.abs(array[i][j-1][k] - array[i][j][k]) <= range && truefalse[i][j-1][k] == false) { //checks if the difference is in the given range and the neighbour is changed before or not.
			array[i][j-1][k] = array[i][j][k];  //the neighbour element is equalized to 'our element'.
			truefalse[i][j-1][k] = true; //the neighbour element marked as changed.
			quantize(i, j-1, k, array, truefalse, range); //the neighbour element sent into method as parameter to be 'our element' in the next call of the method.

		}
		}
		if( k+1<3) { //checks boundaries.
		if(Math.abs(array[i][j][k+1] - array[i][j][k]) <= range && truefalse[i][j][k+1] == false) { //checks if the difference is in the given range and the neighbour is changed before or not.
			array[i][j][k+1] = array[i][j][k];  //the neighbour element is equalized to 'our element'.
			truefalse[i][j][k+1] = true; //the neighbour element marked as changed.
			quantize(i, j, k+1, array, truefalse, range); //the neighbour element sent into method as parameter to be 'our element' in the next call of the method.

		}
		}
		if(k>0 ) { //checks boundaries.
		if(Math.abs(array[i][j][k-1] - array[i][j][k]) <= range && truefalse[i][j][k-1] == false) { //checks if the difference is in the given range and the neighbour is changed before or not.
			array[i][j][k-1] = array[i][j][k];  //the neighbour element is equalized to 'our element'.
			truefalse[i][j][k-1] = true; //the neighbour element marked as changed.
			quantize(i, j, k-1, array, truefalse, range); //the neighbour element sent into method as parameter to be 'our element' in the next call of the method.

		}
		}
	}
	
	public static void mein (int[][][] array, boolean[][][] truefalse, int range) throws FileNotFoundException {
		//this method sends every element of an array to quantize method one by one with parameters such as 3d-boolean to check if they are changed and range. 
		for(int k=0; k<3; k++) { //k is the color dimension.
			for(int j=0; j<array.length; j++) { //j is the row.
				for(int i=0; i<array.length; i++) { //i is the column.
					truefalse[j][i][k]=true; //we are marking the starting element as changed because it is not changed before.
					quantize(j,i,k, array, truefalse, range); //we are starting a new recursive loop.
				}
			}
		}
		reWrite(array, "quantized.ppm"); //after all elements marked as changed, our 3d-array takes the final form of itself, and it is sent to rewrite method to printed out to a new file.
	}

}
