package model;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import model.algorithms.BinaryInsertionSort;
import model.algorithms.BubbleSort;
import model.algorithms.CocktailSort;
import model.algorithms.CombSort;
import model.algorithms.CountingSort;
import model.algorithms.CycleSort;
import model.algorithms.DoubleSelectionSort;
import model.algorithms.GnomeSort;
import model.algorithms.HeapSort;
import model.algorithms.InsertionSort;
import model.algorithms.MergeSort;
import model.algorithms.OddEvenSort;
import model.algorithms.QuickSort;
import model.algorithms.RadixSort;
import model.algorithms.SelectionSort;
import model.algorithms.ShellSort;
import model.algorithms.SortAlgorithm;
import view.graphics.Bars;
import view.graphics.Circle;
import view.graphics.DisparityDots;
import view.graphics.Histogram;
import view.graphics.InnerCircle;
import view.graphics.Line;
import view.graphics.Pyramid;
import view.graphics.Swirl;
import view.graphics.UIGraphic;

public class PropertiesFile {

    private Properties prop;

    private static PropertiesFile uniqueInstance;

    private PropertiesFile() {
        this.getPropertiesFile();
    }

    public static PropertiesFile getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PropertiesFile();
        }
        return uniqueInstance;
    }

    private void getPropertiesFile() {
        prop = new Properties();
        String propFileName = "config.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Arquivo de propriedades '" + propFileName + "' não foi encontrado.\nCarregando valores padrão.");
        }
    }

    public UIGraphic[] getGraphics() {
        UIGraphic[] ret;
        String property = "graphics";
        String algProp = prop.getProperty(property);
        if (algProp == null) {
            System.out.println("Propriedade '" + property + "' não foi encontrada.\nCarregando valor padrão.");
            ret = new UIGraphic[]{new Bars(), new Circle(), new DisparityDots(), new Histogram(), new InnerCircle(), new Line(), new Pyramid(), new Swirl()};
        } else {
            String[] graphics = algProp.split(",");
            ret = new UIGraphic[graphics.length];
            for (int i = 0; i < graphics.length; i++) {
                ret[i] = this.getGraphic(graphics[i]);
            }
        }
        return ret;
    }

    private UIGraphic getGraphic(String g) {
        UIGraphic ret = null;
        try {
            ret = (UIGraphic) Class.forName("view.graphics." + g).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | NoSuchMethodException | InvocationTargetException e) {
            System.out.println(e);
        }
        return ret;
    }

    public SortAlgorithm[] getAlgorithms() {
        SortAlgorithm[] ret;
        String property = "algorithms";
        String algProp = prop.getProperty(property);
        if (algProp == null) {
            System.out.println("Propriedade '" + property + "' não foi encontrada.\nCarregando valor padrão.");
            ret = new SortAlgorithm[]{new BinaryInsertionSort(), new BubbleSort(), new CocktailSort(), new CombSort(), new CountingSort(), new CycleSort(), new DoubleSelectionSort(), new GnomeSort(), new HeapSort(), new InsertionSort(), new MergeSort(), new OddEvenSort(), new QuickSort(), new RadixSort(), new SelectionSort(), new ShellSort()};
        } else {
            String[] algorithms = algProp.split(",");
            ret = new SortAlgorithm[algorithms.length];
            for (int i = 0; i < algorithms.length; i++) {
                ret[i] = this.getAlgorithm(algorithms[i]);
            }
        }
        return ret;
    }

    private SortAlgorithm getAlgorithm(String g) {
        SortAlgorithm ret = null;
        try {
            ret = (SortAlgorithm) Class.forName("model.algorithms." + g).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            System.out.println(e);
        }
        return ret;
    }

    public int getDelay() {
        int delay = 5;
        String property = "delay";
        String algProp = prop.getProperty(property);
        if (algProp == null) {
            System.out.println("Propriedade '" + property + "' não foi encontrada.\nCarregando valor padrão.");
        } else {
            try {
                delay = Integer.parseInt(algProp);
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
        return delay;
    }

    public int getNumbersQuantity() {
        int qtd = 25;
        String property = "numbersQuantity";
        String algProp = prop.getProperty(property);
        if (algProp == null) {
            System.out.println("Propriedade '" + property + "' não foi encontrada.\nCarregando valor padrão.");
        } else {
            try {
                qtd = Integer.parseInt(algProp);
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
        return qtd;
    }
}
