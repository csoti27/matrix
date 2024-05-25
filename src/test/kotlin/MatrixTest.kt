package org.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class MatrixTest {

    @Test
    fun testGet() {
        val matrix = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))
        assertEquals(1, matrix[0, 0])
        assertEquals(2, matrix[0, 1])
        assertEquals(3, matrix[1, 0])
        assertEquals(4, matrix[1, 1])
    }

    @Test
    fun testSet() {
        val matrix = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))
        matrix[0, 0] = 5
        assertEquals(5, matrix[0, 0])
    }

    @Test
    fun testPlus() {
        val matrix1 = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))
        val matrix2 = Matrix(arrayOf(
            intArrayOf(5, 6),
            intArrayOf(7, 8)
        ))
        val result = matrix1 + matrix2
        assertEquals(6, result[0, 0])
        assertEquals(8, result[0, 1])
        assertEquals(10, result[1, 0])
        assertEquals(12, result[1, 1])
    }

    @Test
    fun testMinus() {
        val matrix1 = Matrix(arrayOf(
            intArrayOf(5, 6),
            intArrayOf(7, 8)
        ))
        val matrix2 = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))
        val result = matrix1 - matrix2
        assertEquals(4, result[0, 0])
        assertEquals(4, result[0, 1])
        assertEquals(4, result[1, 0])
        assertEquals(4, result[1, 1])
    }

    @Test
    fun testTimes() {
        val matrix1 = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))
        val matrix2 = Matrix(arrayOf(
            intArrayOf(5, 6),
            intArrayOf(7, 8)
        ))
        val result = matrix1 * matrix2
        assertEquals(19, result[0, 0])
        assertEquals(22, result[0, 1])
        assertEquals(43, result[1, 0])
        assertEquals(50, result[1, 1])
    }

    @Test
    fun testTranspose() {
        val matrix = Matrix(arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        ))
        val result = matrix.transpose()
        assertEquals(1, result[0, 0])
        assertEquals(4, result[0, 1])
        assertEquals(2, result[1, 0])
        assertEquals(5, result[1, 1])
        assertEquals(3, result[2, 0])
        assertEquals(6, result[2, 1])
    }

    @Test
    fun testDeterminant() {
        val matrix = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))
        val determinant = matrix.determinant()
        assertEquals(-2, determinant)
    }

    @Test
    fun testEquals() {
        val matrix1 = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))
        val matrix2 = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))
        val matrix3 = Matrix(arrayOf(
            intArrayOf(5, 6),
            intArrayOf(7, 8)
        ))
        assertTrue(matrix1 == matrix2)
        assertFalse(matrix1 == matrix3)
    }

    @Test
    fun testHashCode() {
        val matrix1 = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))
        val matrix2 = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))
        assertEquals(matrix1.hashCode(), matrix2.hashCode())
    }

    @Test
    fun testToString() {
        val matrix = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))
        val expectedString = "1 2\n3 4"
        assertEquals(expectedString, matrix.toString())
    }
}
