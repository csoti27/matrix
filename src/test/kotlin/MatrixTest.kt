package org.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class MatrixTest {

    @Test
    fun testGet() {
        val matrix = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))

        val expectedMatrix = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))

        assertEquals(expectedMatrix, matrix)
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

        val expectedMatrix = Matrix(arrayOf(
            intArrayOf(6, 8),
            intArrayOf(10, 12)
        ))

        assertEquals(expectedMatrix, result)
    }

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

        val expectedMatrix = Matrix(arrayOf(
            intArrayOf(4, 4),
            intArrayOf(4, 4)
        ))

        assertEquals(expectedMatrix, result)
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

        val expectedMatrix = Matrix(arrayOf(
            intArrayOf(19, 22),
            intArrayOf(43, 50)
        ))

        assertEquals(expectedMatrix, result)
    }

    @Test
    fun testTranspose() {
        val matrix = Matrix(arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        ))

        val result = matrix.transpose()

        val expectedMatrix = Matrix(arrayOf(
            intArrayOf(1, 4),
            intArrayOf(2, 5),
            intArrayOf(3, 6)
        ))

        assertEquals(expectedMatrix, result)
    }

    @Test
    fun testDeterminant() {
        val matrix = Matrix(arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        ))
        val determinant = matrix.determinant()
        assertEquals(0, determinant)
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

    // Error path tests
    @Test
    fun testEmptyMatrix() {
        val exception = assertThrows<IllegalArgumentException> {
            Matrix(arrayOf())
        }
        assertEquals("Matrix cannot be empty.", exception.message)
    }

    @Test
    fun testInconsistentRowSizes() {
        val exception = assertThrows<IllegalArgumentException> {
            Matrix(arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3)
            ))
        }
        assertEquals("All rows must have the same number of columns.", exception.message)
    }

    @Test
    fun testGetOutOfBounds() {
        val matrix = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))
        var exception = assertThrows<IllegalArgumentException> {
            matrix[2, 0]
        }
        assertEquals("Row index 2 out of bounds.", exception.message)

        exception = assertThrows<IllegalArgumentException> {
            matrix[0, 3]
        }
        assertEquals("Column index 3 out of bounds.", exception.message)
    }

    @Test
    fun testSetOutOfBounds() {
        val matrix = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))
        var exception = assertThrows<IllegalArgumentException> {
            matrix[2, 0] = 5
        }
        assertEquals("Row index 2 out of bounds.", exception.message)

        exception = assertThrows<IllegalArgumentException> {
            matrix[0, 3] = 5
        }
        assertEquals("Column index 3 out of bounds.", exception.message)
    }

    @Test
    fun testPlusDifferentDimensions() {
        val matrix1 = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))
        val matrix2 = Matrix(arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        ))

        val exception = assertThrows<IllegalArgumentException> {
            matrix1 + matrix2
        }
        assertEquals("Matrices must have the same dimensions to be added.", exception.message)
    }

    @Test
    fun testMinusDifferentDimensions() {
        val matrix1 = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))
        val matrix2 = Matrix(arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        ))

        val exception = assertThrows<IllegalArgumentException> {
            matrix1 - matrix2
        }
        assertEquals("Matrices must have the same dimensions to be subtracted.", exception.message)
    }

    @Test
    fun testTimesInvalidDimensions() {
        val matrix1 = Matrix(arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        ))
        val matrix2 = Matrix(arrayOf(
            intArrayOf(5, 6, 7)
        ))

        val exception = assertThrows<IllegalArgumentException> {
            matrix1 * matrix2
        }
        assertEquals("Number of columns in the first matrix must be equal to the number of rows in the second matrix.", exception.message)
    }

    @Test
    fun testDeterminantNonSquare() {
        val matrix = Matrix(arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        ))

        val exception = assertThrows<IllegalArgumentException> {
            matrix.determinant()
        }
        assertEquals("Determinant is only defined for square matrices.", exception.message)
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
