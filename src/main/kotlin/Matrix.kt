package org.example

import java.util.*

class Matrix(private val values: Array<IntArray>) : Iterable<Int> {

    val numRows: Int = values.size
    val numCols: Int = if (values.isNotEmpty()) values[0].size else 0

    init {
        require(values.isNotEmpty()) { "Matrix cannot be empty." }
        val columnCount = values.getOrNull(0)?.size ?: 0
        for (row in values) {
            require(row.size == columnCount) { "All rows must have the same number of columns." }
        }
    }

    operator fun get(row: Int, col: Int): Int {
        require(row in 0 ..< numRows) { "Row index $row out of bounds." }
        require(col in 0 ..< numCols) { "Column index $col out of bounds." }
        return values[row][col]
    }

    operator fun set(row: Int, col: Int, value: Int) {
        require(row in 0 ..< numRows) { "Row index $row out of bounds." }
        require(col in 0 ..< numCols) { "Column index $col out of bounds." }
        values[row][col] = value
    }

    operator fun plus(input: Matrix): Matrix {
        require(this.numRows == input.numRows && this.numCols == input.numCols) { "Matrices must have the same dimensions to be added." }
        val result = Array(this.numRows) { IntArray(this.numCols) }
        for (i in 0 ..< numRows) {
            for (j in 0 ..< numCols) {
                result[i][j] = this[i, j] + input[i, j]
            }
        }
        return Matrix(result)
    }

    operator fun minus(input: Matrix): Matrix {
        require(this.numRows == input.numRows && this.numCols == input.numCols) { "Matrices must have the same dimensions to be subtracted." }
        val result = Array(this.numRows) { IntArray(this.numCols) }
        for (i in 0 ..< numRows) {
            for (j in 0 ..< numCols) {
                result[i][j] = this[i, j] - input[i, j]
            }
        }
        return Matrix(result)
    }

    operator fun times(input: Matrix): Matrix {
        require(this.numCols == input.numRows) { "Number of columns in the first matrix must be equal to the number of rows in the second matrix." }
        val result = Array(this.numRows) { IntArray(input.numCols) }
        for (i in 0 ..< numRows) {
            for (j in 0 ..< input.numCols) {
                for (k in 0 ..< numCols) {
                    result[i][j] += this[i, k] * input[k, j]
                }
            }
        }
        return Matrix(result)
    }

    fun transpose(): Matrix {
        val result = Array(this.numCols) { IntArray(this.numRows) }
        for (i in 0 ..< numRows) {
            for (j in 0 ..< numCols) {
                result[j][i] = this[i, j]
            }
        }
        return Matrix(result)
    }

    // Determinant is implemented for 2x2 matrices only
    fun determinant(): Int {
        require(this.numRows == this.numCols) { "Determinant is only defined for square matrices." }
        if (this.numRows == 2) {
            return this[0, 0] * this[1, 1] - this[0, 1] * this[1, 0]
        }
        var det = 0
        for (col in 0 ..< numCols) {
            det += (if (col % 2 == 0) 1 else -1) * this[0, col] * minor(0, col).determinant()
        }
        return det
    }

    private fun minor(row: Int, col: Int): Matrix {
        val result = Array(this.numRows - 1) { IntArray(this.numCols - 1) }
        for (i in 0 ..< numRows) {
            if (i == row) continue
            for (j in 0 ..< numCols) {
                if (j == col) continue
                result[i - if (i > row) 1 else 0][j - if (j > col) 1 else 0] = this[i, j]
            }
        }
        return Matrix(result)
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            private var currentRow = 0
            private var currentCol = 0

            override fun hasNext(): Boolean {
                return currentRow < numRows && currentCol < numCols
            }

            override fun next(): Int {
                val value = values[currentRow][currentCol]
                if (++currentCol == numCols) {
                    currentCol = 0
                    currentRow++
                }
                return value
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Matrix) return false

        if (numRows != other.numRows || numCols != other.numCols) return false
        for (i in 0 ..< numRows) {
            if (!values[i].contentEquals(other.values[i])) return false
        }
        return true
    }

    override fun hashCode(): Int {
        var result = Objects.hash(numRows, numCols)
        for (row in values) {
            result = 31 * result + row.contentHashCode()
        }
        return result
    }

    override fun toString(): String {
        return values.joinToString(separator = "\n") { row -> row.joinToString(separator = " ") }
    }
}