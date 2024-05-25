package org.example

import java.util.*
class Matrix(private val values: Array<IntArray>) : Iterable<Int> {

    val numRows: Int = values.size
    val numCols: Int = values[0].size

    init {
        require(values.isNotEmpty())
        val columnCount = values[0].size
        for (row in values) {
            require(row.size == columnCount)
        }
    }

    operator fun get(row: Int, col: Int): Int {
        require(row in 0 until numRows)
        require(col in 0 until numCols)
        return values[row][col]
    }

    operator fun set(row: Int, col: Int, value: Int) {
        require(row in 0 until numRows)
        require(col in 0 until numCols) 
        values[row][col] = value
    }

    operator fun plus(input: Matrix): Matrix {
        require(this.numRows == input.numRows && this.numCols == input.numCols)
        val result = Array(this.numRows) { IntArray(this.numCols) }
        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                result[i][j] = this[i, j] + input[i, j]
            }
        }
        return Matrix(result)
    }

    operator fun minus(input: Matrix): Matrix {
        require(this.numRows == input.numRows && this.numCols == input.numCols)
        val result = Array(this.numRows) { IntArray(this.numCols) }
        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                result[i][j] = this[i, j] - input[i, j]
            }
        }
        return Matrix(result)
    }

    operator fun times(input: Matrix): Matrix {
        require(this.numCols == input.numRows)
        val result = Array(this.numRows) { IntArray(input.numCols) }
        for (i in 0 until numRows) {
            for (j in 0 until input.numCols) {
                for (k in 0 until numCols) {
                    result[i][j] += this[i, k] * input[k, j]
                }
            }
        }
        return Matrix(result)
    }

    fun transpose(): Matrix {
        val result = Array(this.numCols) { IntArray(this.numRows) }
        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                result[j][i] = this[i, j]
            }
        }
        return Matrix(result)
    }

    //Determinant is implemented for 2x2 matrices only
    fun determinant(): Int {
        require(this.numRows == 2 && this.numCols == 2) 
        return this[0, 0] * this[1, 1] - this[0, 1] * this[1, 0]
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
        for (i in 0 until numRows) {
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

