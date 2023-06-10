import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import menu.Menu;
import menu.MenuOperacoes;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        /* MENU INTERATIVO */

        // Scanner scanner = new Scanner(System.in);
        removeCommentedLines("Teste_Input.txt", "input.txt");
        Scanner scanner = new Scanner(new File("input.txt"));
        Menu menu = new Menu(scanner);
        MenuOperacoes option;

        // Executa menu (imprime, recebe a opcao e executa a opcao) de acordo com a opcao passada
        do {
            menu.showMenu();
            option = menu.readOptionMenu();
            menu.runMenuOption(option);
        } while (option != MenuOperacoes.SAIR);

        scanner.close();
    }

    public static void removeCommentedLines(String filePath, String fileTarget) {
        try {
            // Open the input file for reading
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // Create a temporary file for storing the modified content
            String tempFilePath = fileTarget;
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePath));

            // Read each line from the input file
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line starts with "//"
                if (!line.trim().startsWith("//") && !line.trim().isEmpty()) {
                    // Write the line to the temporary file
                    writer.write(line);
                    writer.newLine();
                }
            }

            // Close the input and output files
            reader.close();
            writer.close();

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}