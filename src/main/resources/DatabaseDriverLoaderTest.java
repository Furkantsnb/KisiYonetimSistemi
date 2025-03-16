try {
    Class.forName("org.postgresql.Driver");
    System.out.println("Driver successfully loaded!");
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}