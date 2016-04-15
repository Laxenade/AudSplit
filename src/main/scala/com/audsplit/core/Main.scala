package com.audsplit.core

import java.io.FileReader

import com.esotericsoftware.yamlbeans.YamlReader

import scala.sys.process._
import scalaj.collection.Imports._

object Main extends App {
    val reader = new YamlReader(new FileReader(System.getProperty("user.dir") + "\\Startup.yml"))
    val data = reader.read()
    val map = data.asInstanceOf[java.util.HashMap[Object, Object]].asScala
    val source = map("Source").asInstanceOf[String]
    val output = map("Output").asInstanceOf[String]
    val format = map("Format").asInstanceOf[String]
    val path = map("Path").asInstanceOf[java.util.ArrayList[java.util.HashMap[String, String]]].asScala
    val pathMap = path.map(_.asScala).map(_.head).toMap[String, String]
    val muxer = pathMap("muxer")
    val mp4box = pathMap("mp4box")
    val times = map("Time").asInstanceOf[java.util.ArrayList[Object]].asScala
    val timeList = times.par.map(_.asInstanceOf[java.util.HashMap[Object, Object]].asScala).
        map(_.head).map(x => (x._1.asInstanceOf[String].trim, x._2.asInstanceOf[String].trim)).
        map(x => (x._1, x._2.split("\\s*[-,_]\\s*"))).map(x => (x._1, x._2(0), x._2(1)))
    val finalList = format match {
        case x if x.toUpperCase == "M" =>
            timeList.map(x => (x._1, x._2.split(":"), x._3.split(":"))).map(x => (x._1, x._2(0).toDouble * 60 + x._2(1).toDouble, x._3(0).toDouble * 60 + x._3(1).toDouble)).toList
        case x if x.toUpperCase == "S" =>
            timeList.map(x => (x._1, x._2.toDouble, x._3.toDouble)).toList
        case _ => List()
    }
    finalList.map(x => (x._1, s"${x._2}:${x._3}")).
        map(x => s"""$mp4box -splitx ${x._2} "$source" -out "$output/${x._1}.m4a"""").foreach(_ !)
}
