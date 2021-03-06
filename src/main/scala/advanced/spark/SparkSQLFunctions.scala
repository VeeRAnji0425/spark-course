package advanced.spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{ min, max, avg }
import org.apache.spark.sql.functions.{ upper, length, lower }

object SparkSQLFunctions {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Cloudera Sample Job")
      .master("yarn")
      .config("spark.hadoop.fs.defaultFS", "hdfs://192.168.31.14:8020")
      .config("spark.hadoop.yarn.resourcemanager.address", "192.168.31.14:8032")
      .config("spark.yarn.jars", "hdfs://192.168.31.14:8020/user/talentorigin/jars/*.jar")
      .config("spark.hadoop.yarn.application.classpath", "$HADOOP_CONF_DIR,$HADOOP_COMMON_HOME/*,$HADOOP_COMMON_HOME/lib/*,$HADOOP_HDFS_HOME/*,$HADOOP_HDFS_HOME/lib/*,$HADOOP_MAPRED_HOME/*,$HADOOP_MAPRED_HOME/lib/*,$HADOOP_YARN_HOME/*,$HADOOP_YARN_HOME/lib/*")
      .getOrCreate()

    val stocks = spark.read.options(Map("header" -> "true", "inferSchema" -> "true"))
      .csv("datasets/nse-stocks/nse-stocks-data.csv")

    //String Functions
//    stocks.select(lower(stocks.col("SYMBOL"))).show()
//    stocks.select(lower(stocks("SYMBOL"))).show()

    //Aggregate Functions
    stocks.select(min(stocks.col("OPEN"))).show()
  }
}