package com.test

import scala.io._


/**
 * Created by Honda on 2016/1/30.
 */

import java.io._
import org.json4s._
import org.json4s.jackson.JsonMethods._

object Json4sTest
{
    def main(args: Array[String])
    {
        val dir = new File("D:\\Sample Data\\Youbike\\youboke-1");

        val fileList = dir.listFiles().filter(_.isFile)
        fileList.foreach
        {
            file =>

                println(file)
                val isr = new InputStreamReader(new FileInputStream( file), "UTF-8")
                val read = new BufferedReader(isr)

                val line = read.readLine()
                //                val jsonLines = Source.fromFile("D:\\output.txt", "UTF-8").getLines()
                //                val stationList = for (line <- jsonLines)

                //println(line)
                val jsonObj = parse(line)
                val retCode = jsonObj \\ "retCode"
                val stationObjs = jsonObj \\ "retVal" \\ "sna"
                val stationSize = Range(1, stationObjs.children.size, 1)

                val result = stationSize.map
                {
                    idx =>
                        val stationId = "%04d".format(idx)
                        val sna = (jsonObj \\ "retVal" \\ stationId \\ "sna").values
                        val sno = (jsonObj \\ "retVal" \\ stationId \\ "sno").values
                        val tot = (jsonObj \\ "retVal" \\ stationId \\ "tot").values
                        val sbi = (jsonObj \\ "retVal" \\ stationId \\ "sbi").values
                        val lat = (jsonObj \\ "retVal" \\ stationId \\ "lat").values
                        val lng = (jsonObj \\ "retVal" \\ stationId \\ "lng").values
                        val ar = (jsonObj \\ "retVal" \\ stationId \\ "ar").values
                        val mday = (jsonObj \\ "retVal" \\ stationId \\ "mday").values
                        if (sna.isInstanceOf[String])
                        {
                            val year = mday.toString.substring(0, 4)
                            val month = mday.toString.substring(4, 6)
                            val day = mday.toString.substring(6, 8)
                            val hour = mday.toString.substring(8, 10)

                            (sno, sna, tot, sbi, lat, lng, ar, mday, year, month, day, hour, sbi.toString.toDouble / tot.toString.toDouble)


                        }
                        else
                        {
                            (0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
                        }


                }



                // 過濾掉空值會是Map()
                //            result.filter(_._2.isInstanceOf[String]).map(_.productIterator.toList.mkString(",")).foreach(println)
                //            val snaList = for {
                result.filter(_._1 == "0172").map(_.productIterator.mkString(",")).foreach(println);
                result.filter(_._1 == "0172")
            //                JString(sna) <- (jsonObj \\ "retVal" \\ "sna").children
            //                JString(sno) <- (jsonObj \\ "retVal" \\ "sno").children
            //                JString(tot) <- (jsonObj \\ "retVal" \\ "tot").children
            //                JString(sbi) <- (jsonObj \\ "retVal" \\ "sbi").children
            //                JString(lat) <- (jsonObj \\ "retVal" \\ "lat").children
            //                JString(lng) <- (jsonObj \\ "retVal" \\ "lng").children
            //            } yield (sno,sna)
            //            println("end")
            //            println(snaList)

            //            stationSize.map
            //            {
            //                idx=>
            //                    val stationId = "%04d".format(idx)
            //                    val sna = jsonObj \\ "retVal" \\ "0001" \\ "sna"
            //                    sna.
            //
            //            }

            //println( (jsonObj \\ "retVal" \\ "0001" \\"sna").values )


        }

    }


}
