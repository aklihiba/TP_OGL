package com.test;

import com.exception.NoSquareException;
import com.model.Matrix;
import com.service.MatrixMathematics;

import static org.junit.Assert.*;

public class MatrixMathematicsTest {

    @org.junit.Test
    public void determinant_1() {

        double[][] array = {{3}};
        Matrix data = new Matrix(array);
        double det=0;
        try {
            det  = MatrixMathematics.determinant(data);
        } catch (NoSquareException e) {
            e.printStackTrace();
        }
        assertEquals(3,det,0);
    }


    @org.junit.Test
    public void determinant_2() {

        double[][] array = {{1,2},{3,2}};
        Matrix data = new Matrix(array);
        double det=0;
        try {
           det  = MatrixMathematics.determinant(data);
        } catch (NoSquareException e) {
            e.printStackTrace();
        }
        assertEquals(-4,det,0);
    }

    @org.junit.Test
    public void determinant_3(){
        double[][] array = {{1,2,3},{3,2,1},{0,4,3}};
        Matrix data = new Matrix(array);
        double det=0;
        try {
            det  = MatrixMathematics.determinant(data);
        } catch (NoSquareException e) {
            e.printStackTrace();
        }
        assertEquals(20,det,0);
    }


    @org.junit.Test(expected = NoSquareException.class)
    public  void determinant_exception() throws NoSquareException{
        double[][] array = {{1,2},{2,2},{3,3}};
        Matrix data = new Matrix(array);
        double det=0;
        det  = MatrixMathematics.determinant(data);

    }

    @org.junit.Test
    public void createSubMatrix() {
    }
}