package com.test

import java.io._

/**
 * Created by Honda on 2016/4/20.
 */
object Merger
{
    def main(args: Array[String])
    {
        val f = new File("D:\\Sample Data\\Youbike\\youboke-1")
        val fileList = f.list()

        val stb = new StringBuffer()
        val outputF = new File("D:\\output.txt")
        val writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream( outputF , true) , "UTF-8"))
        fileList.foreach
        {
            file=>
                println( file)
                val isr = new InputStreamReader(new FileInputStream("D:\\Sample Data\\Youbike\\youboke-1\\" + file), "UTF-8")
                val read = new BufferedReader(isr)

                val content = read.readLine()
                writer.write(content)
                writer.newLine()
        }

        writer.close()

    }
}
