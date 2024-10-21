package com.github.rrickson.scalapocs

import java.sql.{Connection, DriverManager, SQLException}

object DatabaseConnection {
  val jdbcUrl = "jdbc:postgresql://<your-rds-endpoint>:5432/<your-db-name>"  // e.g., "jdbc:postgresql://mydb.xxxxxx.us-east-1.rds.amazonaws.com:5432/mydatabase"
  val username = "<your-db-username>"
  val password = "<your-db-password>"

  def connect(): Connection = {
    var connection: Connection = null
    try {
      // Connect to the PostgreSQL database
      connection = DriverManager.getConnection(jdbcUrl, username, password)
      println("Connected to the PostgreSQL server successfully.")
    } catch {
      case e: SQLException => println(s"Failed to connect to the database: ${e.getMessage}")
    }
    connection
  }
}