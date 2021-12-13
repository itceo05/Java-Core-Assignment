package com.bkap.manager;

import com.bkap.constant.JCConstant;
import com.bkap.entities.Category;
import com.bkap.entities.Product;
import com.bkap.manager.base.IManager;
import com.bkap.util.DataProvider;
import com.bkap.util.InputHelper;
import com.bkap.util.Language;
import com.bkap.util.ReadWriteFile;

import java.sql.ResultSet;
import java.util.*;

public class ProductManager implements IManager<Product, String> {
    Scanner sc = new Scanner(System.in);
    ResultSet resultSet = null;
    ResourceBundle bundle = Language.getBundle();
    Product pro = new Product();
    CategoryManager cateManager = new CategoryManager();
    ReadWriteFile<Product> readWriteFile = new ReadWriteFile<>();

    public void chooseMenu() {
        int choose;

        do {
            do {
                menu();
                try {
                    if (sc.hasNextInt()) {
                        choose = sc.nextInt();
                        break;
                    } else {
                        sc.nextLine();
                        throw new InputMismatchException("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                    }
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                }
            }
            while (true);

            switch (choose) {
                case 1:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [1] : %s", bundle.getString("program"), bundle.getString("displayPro"));
                    chooseMenuDisplay();
                    break;
                case 2:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [2] : %s", bundle.getString("program"), bundle.getString("addPro"));
                    infoAdd();
                    break;
                case 3:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [3] : %s", bundle.getString("program"), bundle.getString("updatePro"));
                    chooseMenuUpdate();
                    break;
                case 4:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [4] : %s", bundle.getString("program"), bundle.getString("removePro"));
                    remove();
                    break;
                case 5:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [4] : %s", bundle.getString("program"), bundle.getString("sortPro"));
                    chooseMenuSort();
                    break;
                case 6:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [5] : %s", bundle.getString("program"), bundle.getString("back"));
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s \n", bundle.getString("continue"));
                    break;
                default:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t %s \n", bundle.getString("default"));
                    break;
            }

        }
        while (choose != 6);
    }

    public void chooseMenuDisplay() {
        int chooseDisplay;
        do {
            do {
                menuDislay();
                try {
                    if (sc.hasNextInt()) {
                        chooseDisplay = sc.nextInt();
                        break;
                    } else {
                        sc.nextLine();
                        throw new InputMismatchException("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                    }
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                }
            }
            while (true);

            switch (chooseDisplay) {
                case 1:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [1] : %s", bundle.getString("program"), bundle.getString("displayPro"));
                    displayAll();
                    break;
                case 2:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [2] : %s", bundle.getString("program"), bundle.getString("displayDetailPro"));
                    detailPro();
                    break;
                case 3:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [3] : %s", bundle.getString("program"), bundle.getString("back"));
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s \n", bundle.getString("continue"));
                    break;
                default:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t %s \n", bundle.getString("default"));
                    break;
            }
        }
        while (chooseDisplay != 3);
    }

    public void chooseMenuUpdate() {
        int chooseUpdate;
        do {
            do {
                menuUpdate();
                try {
                    if (sc.hasNextInt()) {
                        chooseUpdate = sc.nextInt();
                        break;
                    } else {
                        sc.nextLine();
                        throw new InputMismatchException("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                    }
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                }
            }
            while (true);

            switch (chooseUpdate) {
                case 1:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [1] : %s", bundle.getString("program"), bundle.getString("updateProName"));
                    updateName();
                    break;
                case 2:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [2] : %s", bundle.getString("program"), bundle.getString("updatePrice"));
                    updatePrice();
                    break;
                case 3:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [3] : %s", bundle.getString("program"), bundle.getString("updateSale"));
                    updateSalePrice();
                    break;
                case 4:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [4] : %s", bundle.getString("program"), bundle.getString("updateDesc"));
                    updateDesc();
                    break;
                case 5:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [5] : %s", bundle.getString("program"), bundle.getString("updateCateId"));
                    updateCateId();
                    break;
                case 6:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [6] : %s", bundle.getString("program"), bundle.getString("updatePro"));
                    updateAll();
                    break;

                case 7:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [7] : %s", bundle.getString("program"), bundle.getString("back"));
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s \n", bundle.getString("continue"));
                    break;
                default:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t %s \n", bundle.getString("default"));
                    break;
            }
        }
        while (chooseUpdate != 7);
    }

    public void chooseMenuSort() {
        int chooseSort;
        do {
            do {
                menuSort();
                try {
                    if (sc.hasNextInt()) {
                        chooseSort = sc.nextInt();
                        break;
                    } else {
                        sc.nextLine();
                        throw new InputMismatchException("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                    }
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                }
            }
            while (true);

            switch (chooseSort) {
                case 1:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [1] : %s", bundle.getString("program"), bundle.getString("sortProByIncrease"));
                    sortProByIncrease();
                    break;
                case 2:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [2] : %s", bundle.getString("program"), bundle.getString("sortProByPriceReduce"));
                    sortProByPriceIncrease();
                    break;
                case 3:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [3] : %s", bundle.getString("program"), bundle.getString("back"));
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s \n", bundle.getString("continue"));
                    break;
                default:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t %s \n", bundle.getString("default"));
                    break;
            }
        }
        while (chooseSort != 3);
    }

    public void menu() {
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t** ** ** ** ** ** " + bundle.getString("managementPro") + " ** ** ** ** ** **");
        System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  1. " + bundle.getString("displayPro") + ".\t\t\t  **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  2. " + bundle.getString("addPro") + ".\t\t\t\t  **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  3. " + bundle.getString("updatePro") + ".\t\t\t\t  **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  4. " + bundle.getString("removePro") + ".\t\t  **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  5. " + bundle.getString("sortPro") + ".\t\t\t\t  **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  6. " + bundle.getString("back") + ".\t\t\t\t\t\t  **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t** ** ** ** ** ** ** *** *** *** *** **  ** ** ** **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + bundle.getString("chooseMenu") + ": ");
    }

    public void menuDislay() {
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t** ** ** ** ** ** " + bundle.getString("displayPro") + " ** ** ** ** ** **");
        System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  1. " + bundle.getString("displayPro") + ".\t\t\t    **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  2. " + bundle.getString("displayDetailPro") + ".\t\t**");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  3. " + bundle.getString("back") + ".\t\t\t\t\t\t    **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t** ** ** ** ** ** ** *** *** *** ** **  ** ** ** ** **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + bundle.getString("chooseMenu") + ": ");
    }

    public void menuUpdate() {
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ** ** ** ** *** " + bundle.getString("updatePro") + " *** ** ** ** **");
        System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  **\t\t\t  1. " + bundle.getString("proName") + ".\t\t\t     **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  **\t\t\t  2. " + bundle.getString("price") + ".\t\t\t     **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  **\t\t\t  3. " + bundle.getString("salePrice") + ".\t\t\t **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  **\t\t\t  4. " + bundle.getString("desc") + ".\t\t\t **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  **\t\t\t  5. " + bundle.getString("cateId") + ".\t\t\t     **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  **\t\t\t  6. " + bundle.getString("all") + ".\t\t\t\t     **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  **\t\t\t  7. " + bundle.getString("back") + ".\t\t\t\t\t **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ** ** ** ** ** ** *** *** *** *** **  ** ** ** **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("chooseMenu") + ": ");
    }

    public void menuSort() {
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t** ** ** ** ** *** " + bundle.getString("sortPro") + " *** ** ** ** ** **");
        System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  1. " + bundle.getString("sortProByIncrease") + ".\t\t**");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  2. " + bundle.getString("sortProByPriceReduce") + ".\t\t    **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  3. " + bundle.getString("back") + ".\t\t\t\t\t\t    **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t** ** ** ** ** ** ** *** *** *** ** **  ** ** ** ** **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + bundle.getString("chooseMenu") + ": ");
    }

    public Product findByName(String name) {
        String sql = "{call sp_Product_FindName(?)}";

        try {
            resultSet = DataProvider.excuteQuery(sql, name);
            resultSet.next();
            Product findPro = new Product(resultSet);
            return findPro;
        } catch (Exception e) {

        }
        return null;
    }

    public List<Product> findLikeName(String name) {
        List<Product> listPro = new ArrayList<>();

        String sql = "{call sp_Product_FindLikeName(?)}";

        try {
            resultSet = DataProvider.excuteQuery(sql, name);
            while (resultSet.next()) {
                Product findPro = new Product(resultSet);
                listPro.add(findPro);
            }

        } catch (Exception e) {

        }
        return listPro;
    }

    public Product findByNameU(String id, String name) {
        String sql = "{call sp_Product_FindNameU(?, ?)}";

        try {
            resultSet = DataProvider.excuteQuery(sql, id, name);
            resultSet.next();
            Product findPro = new Product(resultSet);
            return findPro;
        } catch (Exception e) {

        }
        return null;
    }

    public List<Product> findByCate(String cateId) {
        List<Product> listPro = new ArrayList<>();
        String sql = "{call sp_Product_FindByCate(?)}";

        try {
            resultSet = DataProvider.excuteQuery(sql, cateId);
            while (resultSet.next()) {
                Product findPro = new Product(resultSet);
                listPro.add(findPro);
            }
        } catch (Exception e) {

        }
        return listPro;
    }

    public boolean updateName(String id, String name) {
        String sql = "{call sp_Product_UpdateName(?, ?)}";
        return DataProvider.excuteUpdate(sql, id, name) > 0;
    }

    public boolean updatePrice(String id, float price) {
        String sql = "{call sp_Product_UpdatePrice(?, ?)}";
        return DataProvider.excuteUpdate(sql, id, price) > 0;
    }

    public boolean updateSalePrice(String id, float salePrice) {
        String sql = "{call sp_Product_UpdateSale(?, ?)}";
        return DataProvider.excuteUpdate(sql, id, salePrice) > 0;
    }

    public boolean updateDesc(String id, String desc) {
        String sql = "{call sp_Product_UpdateDesc(?, ?)}";
        return DataProvider.excuteUpdate(sql, id, desc) > 0;
    }

    public boolean updateCateId(String id, String cateId) {
        String sql = "{call sp_Product_UpdateDesc(?, ?)}";
        return DataProvider.excuteUpdate(sql, id, cateId) > 0;
    }

    @Override
    public List<Product> getAll() {
        List<Product> listPro = new ArrayList<>();
        String sql = "{call sp_Product_GetAll()}";

        try {
            resultSet = DataProvider.excuteQuery(sql);
            while (resultSet.next()) {
                Product newPro = new Product(resultSet);
                listPro.add(newPro);
            }
        } catch (Exception e) {

        }
        return listPro;
    }

    @Override
    public Product findById(String id) {
        String sql = "{call sp_Product_FindId(?)}";

        try {
            resultSet = DataProvider.excuteQuery(sql, id);
            resultSet.next();
            Product findPro = new Product(resultSet);
            return findPro;
        } catch (Exception e) {

        }

        return null;
    }

    @Override
    public boolean add(Product entity) {
        String sql = "{call sp_Product_Add(?, ?, ?, ?, ?, ?)}";
        return DataProvider.excuteUpdate(sql, entity.getId(), entity.getProName(),
                entity.getPrice(), entity.getSalePrice(), entity.getDescription(), entity.getCategoryID()) > 0;
    }

    @Override
    public boolean updateAll(String id, Product entity) {
        String sql = "{call sp_Product_UpdateAll(?, ?, ?, ?, ?, ?)}";
        return DataProvider.excuteUpdate(sql, id, entity.getProName(),
                entity.getPrice(), entity.getSalePrice(), entity.getDescription(), entity.getCategoryID()) > 0;
    }

    @Override
    public boolean remove(String id) {
        String sql = "{call sp_Product_Remove(?)}";
        return DataProvider.excuteUpdate(sql, id) > 0;
    }

    public void infoAdd() {
        do {
            try {
                String qtyPro = InputHelper.inputString(bundle.getString("qtyPro"));
                if (Integer.parseInt(qtyPro) > 0) {
                    for (int i = 0; i < Integer.parseInt(qtyPro); i++) {
                        System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t   " + bundle.getString("infoPro"), i + 1);
                        do {
                            String id = InputHelper.inputString(bundle.getString("proId"), "required", "length:4", "contains:C");
                            if (findById(id) == null) {
                                pro.setId(id);
                                break;
                            } else {
                                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                            }
                        }
                        while (true);

                        do {
                            String proName = InputHelper.inputString(bundle.getString("proName"), "required", "consists:6-50");
                            if (findByName(proName) == null) {
                                pro.setProName(proName);
                                break;
                            } else {
                                System.out.println(bundle.getString("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError")));
                            }
                        }
                        while (true);

                        do {
                            try {
                                String price = InputHelper.inputString(bundle.getString("price"));
                                if (Float.parseFloat(price) > 0) {
                                    pro.setPrice(Float.parseFloat(price));
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                            }
                        }
                        while (true);

                        do {
                            try {
                                String salePrice = InputHelper.inputString(bundle.getString("salePrice"));
                                if (Float.parseFloat(salePrice) > 0 && Float.parseFloat(salePrice) < pro.getPrice()) {
                                    pro.setSalePrice(Float.parseFloat(salePrice));
                                    break;
                                } else {
                                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                            }
                        }
                        while (true);

                        pro.setDescription(InputHelper.inputString(bundle.getString("desc"), "required"));

                        do {
                            String cateId = InputHelper.inputString(bundle.getString("ofCate"));
                            Category findCate = cateManager.findById(cateId);
                            if (findCate != null) {
                                pro.setCategoryID(cateId);
                                break;
                            } else {
                                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("cateNull"));
                            }
                        }
                        while (true);

                        if (add(pro)) {
                            readWriteFile.writeFile(JCConstant.PATH_P, getAll());
                            System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateSuccess"));
                        } else {
                            System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateError"));
                        }
                    }
                    break;
                } else {
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                }
            } catch (NumberFormatException e) {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
            }
        }
        while (true);
    }

    public void updateAll() {

        do {
            String proId = InputHelper.inputString(bundle.getString("proIdTo"));
            Product findPro = findById(proId);
            if (findPro != null) {
                do {
                    String proName = InputHelper.inputString(bundle.getString("proName") + " ( " + findPro.getProName() + " )", "required", "consists:6-50");
                    if (findByNameU(proId, proName) == null) {
                        pro.setProName(proName);
                        break;
                    } else {
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                    }
                }
                while (true);

                do {
                    try {
                        String price = InputHelper.inputString(bundle.getString("price") + " ( " + findPro.getPrice() + " )");
                        if (Float.parseFloat(price) > 0) {
                            pro.setPrice(Float.parseFloat(price));
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                    }
                }
                while (true);

                do {
                    try {
                        String salePrice = InputHelper.inputString(bundle.getString("salePrice") + " ( " + findPro.getSalePrice() + " )");
                        if (Float.parseFloat(salePrice) > 0 && Float.parseFloat(salePrice) < pro.getPrice()) {
                            pro.setSalePrice(Float.parseFloat(salePrice));
                            break;
                        } else {
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                    }
                }
                while (true);

                pro.setDescription(InputHelper.inputString(bundle.getString("desc") + " ( " + findPro.getDescription() + " )"));

                do {
                    String cateId = InputHelper.inputString(bundle.getString("ofCate") + " ( " + findPro.getCategoryID() + " )");
                    Category findCate = cateManager.findById(cateId);
                    if (findCate != null) {
                        pro.setCategoryID(cateId);
                        break;
                    } else {
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("cateNull"));
                    }
                }
                while (true);

                if (updateAll(proId, pro)) {
                    readWriteFile.writeFile(JCConstant.PATH_P, getAll());
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateSuccess"));
                } else {
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateError"));
                }

                break;
            } else {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("proNull"));
            }
        }
        while (true);
    }

    public void updateName() {
        do {
            String proId = InputHelper.inputString(bundle.getString("proIdTo"));
            Product findPro = findById(proId);
            if (findPro != null) {
                do {
                    String proName = InputHelper.inputString(bundle.getString("proName") + " ( " + findPro.getProName() + " )", "required", "consists:6-50");
                    if (findByNameU(proId, proName) == null) {
                        pro.setProName(proName);
                        break;
                    } else {
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                    }
                }
                while (true);

                if (updateName(proId, pro.getProName())) {
                    readWriteFile.writeFile(JCConstant.PATH_P, getAll());
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateSuccess"));
                } else {
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateError"));
                }
                break;
            } else {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("proNull"));
            }
        }
        while (true);
    }

    public void updateSalePrice() {
        do {
            String proId = InputHelper.inputString(bundle.getString("proIdTo"));
            Product findPro = findById(proId);
            if (findPro != null) {
                do {
                    try {
                        String salePrice = InputHelper.inputString(bundle.getString("salePrice")  + " ( " + findPro.getSalePrice() + " )");
                        if (Float.parseFloat(salePrice) > 0 && Float.parseFloat(salePrice) < findPro.getPrice()) {
                            pro.setSalePrice(Float.parseFloat(salePrice));
                            break;
                        } else {
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                    }
                }
                while (true);

                if (updateSalePrice(proId, pro.getSalePrice())) {
                    readWriteFile.writeFile(JCConstant.PATH_P, getAll());
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateSuccess"));
                } else {
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateError"));
                }
                break;
            } else {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("proNull"));
            }
        }
        while (true);
    }

    public void updatePrice() {
        do {
            String proId = InputHelper.inputString(bundle.getString("proIdTo"));
            Product findPro = findById(proId);
            if (findPro != null) {
                do {
                    try {
                        String price = InputHelper.inputString(bundle.getString("price")  + " ( " + findPro.getPrice() + " )");
                        if (Float.parseFloat(price) > 0) {
                            pro.setPrice(Float.parseFloat(price));
                            break;
                        } else {
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                    }
                }
                while (true);

                if (updatePrice(proId, pro.getPrice())) {
                    readWriteFile.writeFile(JCConstant.PATH_P, getAll());
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateSuccess"));
                } else {
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateError"));
                }
                break;
            } else {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("proNull"));
            }
        }
        while (true);
    }

    public void updateDesc() {
        do {
            String proId = InputHelper.inputString(bundle.getString("proIdTo"));
            Product findPro = findById(proId);
            if (findPro != null) {
                pro.setDescription(InputHelper.inputString(bundle.getString("desc") + " ( " + findPro.getDescription() + " )", "required"));

                if (updateDesc(proId, pro.getDescription())) {
                    readWriteFile.writeFile(JCConstant.PATH_P, getAll());
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateSuccess"));
                } else {
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateError"));
                }
                break;
            } else {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("proNull"));
            }
        }
        while (true);
    }

    public void updateCateId() {
        do {
            String proId = InputHelper.inputString(bundle.getString("proIdTo"));
            Product findPro = findById(proId);
            if (findPro != null) {
                do {
                    String cateId = InputHelper.inputString(bundle.getString("ofCate") + " ( " + findPro.getCategoryID() + " - " + findPro.getCateName() + " )");
                    Category findCate = cateManager.findById(cateId);
                    if (findCate != null) {
                        pro.setCategoryID(cateId);
                        break;
                    } else {
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("cateNull"));
                    }
                }
                while (true);

                if (updateCateId(proId, pro.getCategoryID())) {
                    readWriteFile.writeFile(JCConstant.PATH_P, getAll());
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateSuccess"));
                } else {
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateError"));
                }
                break;
            } else {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("proNull"));
            }
        }
        while (true);
    }

    public void remove() {
        String proId = InputHelper.inputString(bundle.getString("proIdTo"));
        if (findById(proId) != null) {
            if (remove(proId)) {
                readWriteFile.writeFile(JCConstant.PATH_P, getAll());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateSuccess"));
            } else {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateError"));
            }
        } else {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("proNull"));
        }
    }

    public void tieuDe() {
        System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  %s\n", bundle.getString("showPro"));
        System.out.printf("\t\t\t\t\t\t\t+--------------+------------------+------------------+--------------------+--------------------+-------------------+\n");
        System.out.printf("\t\t\t\t\t\t\t|  %-12s|   %-15s|   %-15s|   %-10s   |   %-10s   |  %-10s   |\n",
                bundle.getString("proId"), bundle.getString("proName"), bundle.getString("price"), bundle.getString("salePrice"),
                bundle.getString("desc"), bundle.getString("ofCate"));
        System.out.printf("\t\t\t\t\t\t\t+--------------+------------------+------------------+--------------------+--------------------+-------------------+\n");
    }

    public void displayAll() {
        tieuDe();
        for (Category itemC : cateManager.getAllV1()) {
            System.out.printf("\t\t\t\t\t\t\t|\t\t\t\t\t\t\t\t\t\t\t\t\tDanh Mục: %-53s|\n", itemC.getCateName());
            System.out.printf("\t\t\t\t\t\t\t+--------------+------------------+------------------+--------------------+--------------------+-------------------+\n");
            List<Product> listFindByCate = findByCate(itemC.getId() + "");
            for (Product item : listFindByCate) {
                System.out.printf("\t\t\t\t\t\t\t|\t  %-9s|   %-15s|   %-15s|   %-15s  |  %-18s|  %-15s  |\n",
                        item.getId(), item.getProName(), item.getPrice(), item.getSalePrice(),
                        (item.getDescription().length() > 18) ? item.getDescription().substring(0, 15) + "..." : item.getDescription(),
                        item.getCategoryID() + " - " + item.getCateName());
                System.out.printf("\t\t\t\t\t\t\t+--------------+------------------+------------------+--------------------+--------------------+-------------------+\n");
            }
        }
    }

    public void detailPro() {
        String name = InputHelper.inputString(bundle.getString("proName"), "required");
        List<Product> list = findLikeName(name);
        tieuDe();
        if (list.size() != 0) {
            for (Product item : list) {
                System.out.printf("\t\t\t\t\t\t\t|\t  %-9s|   %-15s|   %-15s|   %-15s  |  %-18s|  %-15s  |\n",
                        item.getId(), item.getProName(), item.getPrice(), item.getSalePrice(),
                        (item.getDescription().length() > 18) ? item.getDescription().substring(0, 15) + "..." : item.getDescription(),
                        item.getCategoryID() + " - " + item.getCateName());
                System.out.printf("\t\t\t\t\t\t\t+--------------+------------------+------------------+--------------------+--------------------+-------------------+\n");
            }
        } else {
            System.out.printf("\t\t\t\t\t\t\t|\t\t\t\t\t\t\t\t\t\t\t[ - Không Có Kết Quả Phù Hợp - ]   \t\t\t\t\t\t\t\t\t   |\n");
            System.out.printf("\t\t\t\t\t\t\t+------------------------------------------------------------------------------------------------------------------+\n");
        }

    }

    public void sortProByIncrease() {
        List<Product> listSort = getAll();
        /*
        * @author : ChinhHT
        * @since : 12/5/2021 7:15 PM
        * @description : Convert ve chuoi chu thuong roi sap het - Bat TH A - a
        * @update :
        *
        * */
        listSort.sort(
                Comparator.comparing(product -> product.getProName().toLowerCase())
        );
        tieuDe();
        for (Product item : listSort) {
            System.out.printf("\t\t\t\t\t\t\t|\t  %-9s|   %-15s|   %-15s|   %-15s  |  %-18s|  %-15s  |\n",
                    item.getId(), item.getProName(), item.getPrice(), item.getSalePrice(),
                    (item.getDescription().length() > 18) ? item.getDescription().substring(0, 15) + "..." : item.getDescription(),
                    item.getCategoryID() + " - " + item.getCateName());
            System.out.printf("\t\t\t\t\t\t\t+--------------+------------------+------------------+--------------------+--------------------+-------------------+\n");
        }
    }

    public void sortProByPriceIncrease() {
        List<Product> listSort = getAll();
        listSort.sort(
                Comparator.comparing(Product::getPrice)
        );
        tieuDe();
        for (Product item : listSort) {
            System.out.printf("\t\t\t\t\t\t\t|\t  %-9s|   %-15s|   %-15s|   %-15s  |  %-18s|  %-15s  |\n",
                    item.getId(), item.getProName(), item.getPrice(), item.getSalePrice(),
                    (item.getDescription().length() > 18) ? item.getDescription().substring(0, 15) + "..." : item.getDescription(),
                    item.getCategoryID() + " - " + item.getCateName());
            System.out.printf("\t\t\t\t\t\t\t+--------------+------------------+------------------+--------------------+--------------------+-------------------+\n");
        }
    }
}
