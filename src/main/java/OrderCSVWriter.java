import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderCSVWriter {

    private static final String FILE_PATH = "src/main/resources/orders.csv";

    public static void writeCoffeeOrder(Coffee coffee) {
        try {
            File file = new File(FILE_PATH);
            FileWriter fileWriter = new FileWriter(file, true);

            if (file.length() > 0) {
                fileWriter.write(System.lineSeparator());
            }

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

            fileWriter.write(
                    coffee.getCustomerName()
                            + "|"
                            + coffee.toFileText()
                            + "|"
                            + String.format("%.2f", coffee.getTotal())
                            + "|"
                            + timestamp
            );

            fileWriter.close();

        } catch (IOException ex) {
            System.out.println("Error writing order to file.");
        }
    }
}