package june.project.util;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {

  Scanner input;

  public Prompt(Scanner input) {
    this.input = input;
  }

  public String inputString(String label) {
    System.out.print(label);
    return input.nextLine();
  }

  public String inputString(String label, String defaultValue) {
    System.out.print(label);
    String value = input.nextLine();
    if (value.length() == 0) {
      return defaultValue;
    }
    return value;
  }

  public int inputInt(String label) {
    System.out.print(label);
    return Integer.parseInt(input.nextLine());
  }

  public int inputInt(String label, int defaultValue) {
    System.out.print(label);
    String value = input.nextLine();
    if (value.length() == 0) {
      return defaultValue;
    }
    return Integer.parseInt(value);
  }

  public Date inputDate(String label) {
    System.out.print(label);
    return Date.valueOf(input.nextLine());
  }

  public Date inputDate(String label, Date defaultValue) {
    System.out.print(label);
    String value = input.nextLine();
    if (value.length() == 0) {
      return defaultValue;
    }
    return Date.valueOf(value);
  }

  public float inputFloat(String label) {
    System.out.print(label);
    return Float.parseFloat(input.nextLine());
  }

  public float inputFloat(String label, float defaultValue) {
    System.out.print(label);
    String value = input.nextLine();
    if (value.length() == 0) {
      return defaultValue;
    }
    return Float.parseFloat(value);
  }

}
