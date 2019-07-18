package spoiled.mango;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SpoiledMangoSolution {

  public static void main(String[] args) {
    
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter The Number Of Matrix Rows: ");
    int matrixRow = scan.nextInt();
     
    System.out.println("Enter The Number Of Matrix Columns: ");
    int matrixCol = scan.nextInt();
     
    int[][] matrix = new int[matrixRow][matrixCol];
    enterMatrixData(scan, matrix, matrixRow, matrixCol);
    
    // Print Matrix Data
    printMatrix(matrix, matrixRow, matrixCol);
    int totalTime = calculateIterationToSpoil(matrix, matrixRow, matrixCol);
    
    System.out.println("Final matrix : " + totalTime);
    printMatrix(matrix, matrixRow, matrixCol);
    
    totalTime = doesAllMangoesSpoiled(matrix, matrixRow, matrixCol) ? totalTime : -1;
    System.out.println("Total number of iteration: " + totalTime);
  }
  
  /**
   * @param matrix
   * @param matrixRow
   * @param matrixCol
   * @return does all mangoes getting sopiled
   */
  public static boolean doesAllMangoesSpoiled(int[][] matrix, int matrixRow, int matrixCol) {
    
    boolean allMangoesSpoiled = true;
    
    for(int row = 0; row < matrixRow; row++ ) {
      for (int column = 0; column < matrixCol; column++) { 
        if(matrix[row][column] == 1) {
          allMangoesSpoiled = false;
          break;
        }
      }
    }
    
    return allMangoesSpoiled;
  }
  
  /**
   * @param matrix
   * @param matrixRow
   * @param matrixCol
   * @return find out total number of iteration
   */
  public static int calculateIterationToSpoil(int[][] matrix, int matrixRow, int matrixCol) {
    
    int value;
    Queue<MatrixElement> spoiledMango = new LinkedList<MatrixElement>();
    int iteration = 0;
    MatrixElement matrixElement;
    boolean canStartNewIteration = true;
    
    initQueueWithSpoiledMango(matrix, matrixRow,
        matrixCol, spoiledMango);
    
    int row, column;
    
    // indicate end of iteration
    spoiledMango.add(new MatrixElement(-1, -1));
    
    while(!spoiledMango.isEmpty()) {
      
      matrixElement = spoiledMango.remove();
      row = matrixElement.getxCordinate();
      column = matrixElement.getyCordinate();
      
      if(row == -1 && column == -1) {
        if(spoiledMango.isEmpty()) {
          return iteration;
        }
        
        iteration += 1;
        canStartNewIteration = true;
        matrixElement = spoiledMango.remove();
        row = matrixElement.getxCordinate();
        column = matrixElement.getyCordinate();
      }
      
      value = matrix[row][column];
      
      if(value == 2) {
        if(row != 0 && matrix[row-1][column] != 0 && matrix[row-1][column] != 2) {
          
          canStartNewIteration = addNewSpoiledMango(matrix, spoiledMango, canStartNewIteration, row-1, column);
        }
        
        if(row != matrixRow-1 && matrix[row+1][column] != 0 && matrix[row+1][column] != 2) {

          canStartNewIteration = addNewSpoiledMango(matrix, spoiledMango, canStartNewIteration, row+1, column);
        }
        
        if(column != 0 && matrix[row][column-1] != 0 && matrix[row][column-1] != 2) {
          
          canStartNewIteration = addNewSpoiledMango(matrix, spoiledMango, canStartNewIteration, row, column-1);
        }
        
        if(column != matrixCol-1 && matrix[row][column+1] != 0 && matrix[row][column+1] != 2) {
          
          canStartNewIteration = addNewSpoiledMango(matrix, spoiledMango, canStartNewIteration, row, column+1);
        }
      }
      
    }
    
    return iteration;
  }

  /**
   * @param matrix
   * @param spoiledMango
   * @param canStartNewIteration
   * @param row
   * @param column
   * @return add new spoiled mango into spoiled mango queue
   */
  private static boolean addNewSpoiledMango(
      int[][] matrix,
      Queue<MatrixElement> spoiledMango,
      boolean canStartNewIteration, int row,
      int column) {
    
    matrix[row][column] = 2;
    spoiledMango.add(new MatrixElement(row, column));
    
    if(canStartNewIteration) {
      spoiledMango.add(new MatrixElement(-1, -1));
      canStartNewIteration = false;
    }
    
    return canStartNewIteration;
  }

  /**
   * @param matrix
   * @param matrixRow
   * @param matrixCol
   * @param newSpoiledMango
   */
  private static void initQueueWithSpoiledMango(
      int[][] matrix, int matrixRow,
      int matrixCol,
      Queue<MatrixElement> newSpoiledMango) {
    for(int row = 0; row < matrixRow; row++ ) {
      for (int column = 0; column < matrixCol; column++) {
        
        if(matrix[row][column] == 2) {
          newSpoiledMango.add(new MatrixElement(row, column));
        }
        
      }
    }
  }
  
  public static void enterMatrixData(Scanner scan,
      int[][] matrix, int matrixRow,
      int matrixCol) {
    System.out.println("Enter Matrix Data");

    for (int i = 0; i < matrixRow; i++) {
      for (int j = 0; j < matrixCol; j++) {
        matrix[i][j] = scan.nextInt();
      }
    }
  }
 
  public static void printMatrix(int[][] matrix,
      int matrixRow, int matrixCol) {
    System.out.println("Your Matrix is : ");

    for (int i = 0; i < matrixRow; i++) {
      for (int j = 0; j < matrixCol; j++) {
        System.out.print(matrix[i][j] + "\t");
      }

      System.out.println();
    }
  }

}
