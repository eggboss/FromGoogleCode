public class testoqa {
	/* * @author  Arun Prasath S * @version 1.2 * * Development Environment        :  JDeveloper 2.0 * Name of the Application        :  ObjectOracleSample.java * Creation/Modification History  : * *    asriniva.in       10-Feb-1999      Created *    neshastr          22-May-2002      Certified on Oracle9i JDeveloper *    Stephen           13-May-2003      Certified on linux */
	
		/** * This sample illustrates retrieval and manipulation of Objects from a * Oracle database using JDBC. * * Objects can be accessed either using oracle.sql.STRUCT or by defining * custom Java Classes to represent the Oracle Object Type. This sample * illustrates access using the weakly typed objects (oracle.sql.STRUCT), while * retrieval using strongly typed objects is illustrated in Object Java Sample. * * In this sample the user can perform DML operations like select, insert * update and delete on a table containing a Object Type Column. * * The gui part of this sample is handled seperately in ObjectOracleFrame.java */
//    Connection connection = null; // Database connection object    ObjectOracleFrame gui; // For Handling the gui related operations    /**   * Constructor which in turn calls the constructor of gui class   */    public ObjectOracleSample() {    try {      gui = new ObjectOracleFrame(this);    } catch(Exception e) { // trap the errors if any      gui.putStatus(" Error :");      gui.appendStatus(" " + e.toString());    }  }    /**   *  Main entry point for the class. Instantiates the root frame and   *  sets up the database connection.   */    public static void main(String[] args) {    ObjectOracleSample oos = new ObjectOracleSample();    oos.dbConnection(); // setup database connectivity  }    /**   * Dispatches the gui events to the appropriate method, which performs   * the required JDBC operations. This method is invoked when event occurs   * in the GUI (like table Selection, Button clicks etc.). This method   * is invoked from the setupListeners section of ObjectOracleFrame.java   */    public void dispatchEvent(String eventName) {        // Get the values from the gui    String name = gui.cityName.getText();    String density = gui.populationDensity.getText();    String state = gui.stateProvince.getText();    String code = gui.cityCode.getText();        //  For dispatching the select event    if(eventName.equals("SELECT")) {      selectRecord(name, density, state, code);    } else {      if(eventName.equals("INSERT")) {        insertRecord(name, density, state, code);      } else {        if(eventName.equals("UPDATE")) {          updateRecord(name, density, state, code);        } else {          if(eventName.equals("DELETE")) {            int row = gui.cityTable.getSelectedRow();            String scode = (String)gui.cityTableModel.getRow(row).elementAt(2);            deleteRecord(scode);            gui.cityTableModel.deleteRow(row);          } else {            if(eventName.equals("EXIT")) /* For dispatching the exit event*/ {              exitApplication();            }          }        }      }    }  }    /**   * This method reads a properties file which is passed as   * the parameter to it and loads it into a java Properties   * object and returns it.   */    private static Properties loadParams(String file) throws IOException {        // Loads a ResourceBundle and creates Properties from it    Properties prop = new Properties();    ResourceBundle bundle = ResourceBundle.getBundle(file);    Enumeration enum = bundle.getKeys();    String key = null;    while(enum.hasMoreElements()) {      key = (String)enum.nextElement();      prop.put(key, bundle.getObject(key));    }    return prop;  }   /**   * Creates a database connection object using DataSource object. Please    * substitute the database connection parameters with appropriate values in   * Connection.properties file   */
//	  private void dbConnection() {    try {      gui.putStatus("Trying to connect to the Database");       // Load the properties file to get the connection information      Properties prop = this.loadParams("Connection");       // Create a OracleDataSource instance      OracleDataSource ods = new OracleDataSource();       // Sets the driver type      ods.setDriverType("thin");       // Sets the database server name      ods.setServerName((String)prop.get("HostName"));       // Sets the database name      ods.setDatabaseName((String)prop.get("SID"));       // Sets the port number      ods.setPortNumber(new Integer((String)prop.get("Port")).intValue());       // Sets the user name      ods.setUser((String)prop.get("UserName"));       // Sets the password      ods.setPassword((String)prop.get("Password"));      connection=ods.getConnection();      gui.putStatus(" Connected to " + prop.get("SID") +                    " Database as " + prop.get("UserName"));     } catch(SQLException ex) { // Trap SQL errors        gui.putStatus(            "Error in Connecting to the Database "+'\n'+ex.toString());    } catch(IOException ex) { // Trap SQL errors        gui.putStatus(            "Error in reading the properties file "+'\n'+ex.toString());    }  }      /**   * This function selects the rows from the TABLE_CITY_OBJ table  based on the   * query condition entered   */    private void selectRecord(String name, String density, String state,       String code) {        // The SQL query for selecting the rows from the TABLE_CITY_OBJ table    // Query can only be done on two fields, CITY CODE and CITY NAME    String query = "SELECT * from TABLE_CITY_OBJ a "+        "WHERE a.OBJCITY.CITY_CODE LIKE ? AND a.OBJCITY.NAME LIKE ?";    try {            // Create a PreparedStatement based on the query in query      PreparedStatement pst = connection.prepareStatement(query);            // If no query condition has been entered, change the bind values      // to select all records      if(code.equals(""))         code = "%";      else         code = "%"+code+"%";      if(name.equals(""))         name = "%";      else
//	        name = "%"+name+"%";            // Bind the PreparedStatement with corresponding values      pst.setString(1, code);      pst.setString(2, name);            // Execute the PreparedStatement and retrieve into an OracleResultSet,      // as we need to retrieve Oracle extension datatypes like STRUCT      OracleResultSet resultSet = (OracleResultSet)pst.executeQuery();            // Loop through the resultset and retrieve the objects      while(resultSet.next()) {                // Use getObject to get the row into oracle.sql.STRUCT        oracle.sql.STRUCT mystruct =             (oracle.sql.STRUCT)(resultSet.getObject(1));                // Call getAttributes method of oracle.sql.STRUCT to get the individual        // column values        Object cols[] = mystruct.getAttributes();                // If column in STRUCT is null set it to an empty string for the JTable        cols[1] = (cols[1] == null) ? "" : cols[1];        cols[2] = (cols[2] == null) ? "" : cols[2];                // Add the row values to the JTable        gui.addToJTable(cols[0], cols[1], cols[2], cols[3]);      }      pst.close(); // Close the statement      gui.appendStatus(" Rows selected");    } catch(SQLException ex) { // trap sql errors      gui.putStatus(" Error in Querying: ");      gui.appendStatus("\n" + ex.toString());    }  }    /**   * This function inserts a new row into TABLE_CITY_OBJ table   */    private void insertRecord(String name, String density, String state,       String code) {    Integer densityint;        // If the field is empty assign 0 to densityint    if(density.compareTo("") == 0) {      densityint = new Integer(0);    }    else /* Else convert the string to equivalent data type*/ {      try {        densityint = new Integer(density);      } catch (Exception e) {        densityint = new Integer(0);      }    }    try {            // construct the object array containing the attribute values for the      // Object to be inserted      Object objArray[] = new Object[4];      objArray[0] = name;      objArray[1] = state;      objArray[2] = code;      objArray[3] = densityint;            // Create the StructDescriptor from the connection      StructDescriptor colStructDesc =           StructDescriptor.createDescriptor("OBJ_TYPE_CITY", connection);            // Construct the Struct from the StructDescriptor and objects      oracle.sql.STRUCT colStruct = new STRUCT(colStructDesc,           connection, objArray);            // Prepare the insert SQL statement using Preparedstatement. We need to      // use OraclePreparedStatement here because we need to bind in an Oracle      // extension type, STRUCT      OraclePreparedStatement ps =           (OraclePreparedStatement)connection.prepareStatement(          "INSERT INTO TABLE_CITY_OBJ VALUES (?)");            // Bind in the STRUCT representing the object to be inserted      ps.setSTRUCT(1, colStruct);      ps.executeUpdate(); // execute the insert statement      ps.close(); // close the prepared statement            // Add the inserted row to the JTable      gui.addToJTable(name, state, code, densityint);      gui.putStatus(" Row inserted");    } catch(SQLException ex) { // trap sql errors      gui.putStatus(" Error in Inserting: ");      gui.appendStatus("\n" + ex.toString());    }  }    /**   * This function updates an existing record in the TABLE_OBJ_CITIES table   **/    private void updateRecord(String name, String density, String state,       String code) {    Integer densityint;        // If the field is empty assign 0 to densityint    if(density.compareTo("") == 0) {      densityint = new Integer(0);    } else /* else convert the string to equivalent data type*/ {      try {        densityint = new Integer(density);      } catch (Exception e) {        densityint = new Integer(0);      }    }    try {            // construct the object array containing the values to be updated      Object objArray[] = new Object[4];      objArray[0] = name;      objArray[1] = state;      objArray[2] = code;      objArray[3] = densityint;            // Create the StructDescriptor from the connection      StructDescriptor colStructDesc =           StructDescriptor.createDescriptor("OBJ_TYPE_CITY", connection);            // Construct the Struct from the StructDescriptor and objects      oracle.sql.STRUCT colStruct = new STRUCT(colStructDesc,           connection, objArray);            // Prepare the update statement using Prepared statement call      OraclePreparedStatement ps =           (OraclePreparedStatement)connection.prepareStatement(          "UPDATE TABLE_CITY_OBJ a SET a.OBJCITY = ? " +           "WHERE a.OBJCITY.CITY_CODE = ?");            // Bind the Object STRUCT and CITY CODE  to preparedstatement      ps.setSTRUCT(1, colStruct);      ps.setString(2, code);      ps.executeUpdate(); // Execute the update statement      ps.close(); // Close the prepared statement      gui.putStatus(" Row updated");            // Call getAttributes method of oracle.sql.STRUCT to get the individual      // column values      Object cols[] = colStruct.getAttributes();            // Update the row values in the JTable      gui.updateJTable(cols[0], cols[1], cols[2], cols[3]);    } catch(SQLException ex) { // trap sql errors      gui.putStatus(" Error in Updating: ");      gui.appendStatus("\n" + ex.toString());    }  }    /**   * This function deletes the selected row from the Object table   */
//	  private void deleteRecord(String code) {    try {            // prepare the delete statement using Prepared statement call      PreparedStatement ps = connection.prepareStatement(          "DELETE FROM TABLE_CITY_OBJ a WHERE a.OBJCITY.CITY_CODE =?");      ps.setString(1, code); // set the values in the statement      ps.executeUpdate(); // execute the delete statement      ps.close(); // close the prepared statement      gui.putStatus("Row deleted");    } catch(SQLException ex) { // catch any exceptions that occur      gui.putStatus(" Error in Deleting: ");      gui.appendStatus("\n" + ex.toString());    }    // clear the selection    gui.cityTable.clearSelection();  }    /**   * Closes the connection and exits from the program   */
//	    private void exitApplication() {    if(connection != null) {      try {        connection.close(); // close the connection      } catch(Exception ex) { // Trap SQL Errors        gui.putStatus(" Error in Closing the connection: ");        gui.appendStatus("\n" + ex.toString());      }    }    System.exit(0);  }}

}