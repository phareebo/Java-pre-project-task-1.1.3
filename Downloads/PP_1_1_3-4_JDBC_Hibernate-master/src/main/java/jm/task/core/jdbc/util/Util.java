package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private Util() {

    } //#2. – Сделать конструктор класса (конструктор по-умолчанию) приватным (чтобы доступ к нему был закрыть за пределами класса, тогда он не сможет возвращать новые объекты):

    private static final String URL = "jdbc:mysql://localhost:3306/pp1";
    private static final String USER = "root";
    private static final String PASSWORD = "Romawka123!";

    private static Connection connection; //#1. – Нужно добавить в класс приватное статическое поле, содержащее одиночный объект:

    public static Connection getConnection() { //#3. – Объявить статический создающий метод, который будет использоваться для получения одиночки:
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}