package utilities;

import com.opencsv.CSVReaderHeaderAware;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvReader {
    public static List<Map<String, String>> readCsvAsMap(String fileName){
        List<Map<String, String>> data = new ArrayList<>();
        try (
                InputStream inputStream = CsvReader.class.getClassLoader().getResourceAsStream(fileName);
             CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new InputStreamReader(inputStream));
             ){
            Map<String, String> row;
            while ((row = reader.readMap()) != null){
                data.add(row);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read csv file as map: " + fileName, e);
        }
        return data;
    }

}
