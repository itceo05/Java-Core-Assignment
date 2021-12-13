package com.bkap.manager;

import com.bkap.constant.JCConstant;
import com.bkap.entities.Category;
import com.bkap.manager.base.IManager;
import com.bkap.util.DataProvider;
import com.bkap.util.InputHelper;
import com.bkap.util.Language;
import com.bkap.util.ReadWriteFile;

import java.sql.ResultSet;
import java.util.*;

public class CategoryManager implements IManager<Category, String> {
    Scanner sc = new Scanner(System.in);
    ResultSet resultSet = null;
    ResourceBundle bundle = Language.getBundle();
    Category cate = new Category();
    ReadWriteFile<Category> readWriteFile = new ReadWriteFile<>();
    ArrayList<String> listCateLv3;

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
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [1] : %s", bundle.getString("program"), bundle.getString("displayCate"));
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
                                System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [1] : %s", bundle.getString("program"), bundle.getString("displayCate"));
                                displayAll();
                                break;
                            case 2:
                                System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [2] : %s", bundle.getString("program"), bundle.getString("displayDetailCate"));
                                detailCate();
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

                    break;
                case 2:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [2] : %s", bundle.getString("program"), bundle.getString("addCate"));
                    infoAdd();
                    break;
                case 3:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [3] : %s", bundle.getString("program"), bundle.getString("updateCate"));
                    update();
                    break;
                case 4:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [4] : %s", bundle.getString("program"), bundle.getString("removeCate"));
                    remove();
                    break;
                case 5:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [5] : %s", bundle.getString("program"), bundle.getString("findCate"));
                    findByName();
                    break;
                case 6:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s [6] : %s", bundle.getString("program"), bundle.getString("back"));
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s \n", bundle.getString("continue"));
                    break;
                default:
                    System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t %s \n", bundle.getString("default"));
                    break;
            }

        }
        while (choose != 6);
    }

    public void menu() {
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t** ** ** ** ** ** " + bundle.getString("managementCate") + " ** ** ** ** ** **");
        System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  1. " + bundle.getString("displayCate") + ".\t\t\t  **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  2. " + bundle.getString("addCate") + ".\t\t\t\t  **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  3. " + bundle.getString("updateCate") + ".\t\t\t\t  **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  4. " + bundle.getString("removeCate") + ".\t\t  **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  5. " + bundle.getString("findCate") + ".\t\t\t\t  **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  6. " + bundle.getString("back") + ".\t\t\t\t\t\t  **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t** ** ** ** ** ** ** *** *** *** *** **  ** ** ** **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + bundle.getString("chooseMenu") + ": ");
    }

    public void menuDislay() {
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t** ** ** ** ** ** " + bundle.getString("displayCate") + " ** ** ** ** ** **");
        System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  1. " + bundle.getString("displayCate") + ".\t\t\t    **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  2. " + bundle.getString("displayDetailCate") + ".\t\t**");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t**\t\t\t  3. " + bundle.getString("back") + ".\t\t\t\t\t\t    **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t** ** ** ** ** ** ** *** *** *** ** **  ** ** ** ** **");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + bundle.getString("chooseMenu") + ": ");
    }

    public List<Category> getByParent(int id) {
        List<Category> cateParent = new ArrayList<>();
        String sql = "{call sp_Category_FindParent(?)}";

        try {
            resultSet = DataProvider.excuteQuery(sql, id);

            while (resultSet.next()) {
                Category newCate = new Category(resultSet);
                cateParent.add(newCate);
            }
        } catch (Exception e) {

        }

        return cateParent;
    }

    public List<Category> findByName(String name) {
        List<Category> findCate = new ArrayList<>();
        String sql = "{call sp_Category_FindLikeName(?)}";

        try {
            resultSet = DataProvider.excuteQuery(sql, name);
            while (resultSet.next()) {
                Category findByName = new Category(resultSet);
                findCate.add(findByName);
            }
        } catch (Exception e) {

        }

        return findCate;
    }

    public List<Category> getAllV1() {
        List<Category> listCate = new ArrayList<>();
        String sql = "{call sp_Category_GetAll_V1()}";

        try {
            resultSet = DataProvider.excuteQuery(sql);

            while (resultSet.next()) {
                Category newCate = new Category(resultSet);
                listCate.add(newCate);
            }
        } catch (Exception e) {

        }

        return listCate;
    }


    @Override
    public List<Category> getAll() {
        List<Category> listCate = new ArrayList<>();
        String sql = "{call sp_Category_GetAll()}";

        try {
            resultSet = DataProvider.excuteQuery(sql);

            while (resultSet.next()) {
                Category newCate = new Category(resultSet);
                listCate.add(newCate);
            }
        } catch (Exception e) {

        }

        return listCate;
    }

    @Override
    public Category findById(String id) {
        String sql = "{call sp_Category_FindId(?)}";

        try {
            resultSet = DataProvider.excuteQuery(sql, id);

            resultSet.next();
            Category findCate = new Category(resultSet);
            return findCate;

        } catch (Exception e) {

        }

        return null;
    }

    @Override
    public boolean add(Category entity) {
        String sql = "{call sp_Category_Add(?, ?, ?)}";

        try {
            return DataProvider.excuteUpdate(sql, entity.getId(), entity.getCateName(), entity.getParentID()) > 0;
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean updateAll(String id, Category entity) {
        String sql = "{call sp_Category_Update(?, ?, ?)}";
        try {
            return DataProvider.excuteUpdate(sql, id, entity.getCateName(), entity.getParentID()) > 0;
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean remove(String id) {
        String sql = "{call sp_Category_Remove(?)}";
        try {
            return DataProvider.excuteUpdate(sql, id) > 0;
        } catch (Exception e) {

        }

        return false;
    }

    public void infoAdd() {

        displayAll();
        do {
            try {
                String qtyCate = InputHelper.inputString(bundle.getString("qtyCate"));
                if (Integer.parseInt(qtyCate) > 0) {
                    for (int i = 0; i < Integer.parseInt(qtyCate); i++) {
                        System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t   " + bundle.getString("infoCate"), i + 1);
                        do {
                            String id = InputHelper.inputString(bundle.getString("cateId"));
                            try {
                                if (Integer.parseInt(id) > 0) {
                                    if (findById((id + "")) == null) {
                                        cate.setId(Integer.parseInt(id));
                                        break;
                                    } else {
                                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                                    }
                                } else {
                                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                                }
                            } catch (Exception e) {
                                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                            }
                        }
                        while (true);

                        cate.setCateName(InputHelper.inputString(bundle.getString("cateName"), "required", "consists:6-30"));

                        do {
                            String parentID = InputHelper.inputString(bundle.getString("cateParen"));
                            boolean inValid = listCateLv3.stream().anyMatch(x -> x.equals(parentID));
                            try {
                                if (Integer.parseInt(parentID) >= 0) {
                                    if ((findById((parentID + "")) != null || Integer.parseInt(parentID) == 0) && !inValid) {
                                        cate.setParentID(Integer.parseInt(parentID));
                                        break;
                                    } else {
                                        System.out.println(bundle.getString("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError")));
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                            }
                        }
                        while (true);

                        if (add(cate)) {
                            readWriteFile.writeFile(JCConstant.PATH_C, getAll());
                            System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateCateSuccess"));
                        } else {
                            System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateCateError"));
                        }
                    }
                    break;
                } else {
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                }
            } catch (Exception e) {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
            }
        }
        while (true);
    }

    public void update() {
        do {
            int id = InputHelper.inputInt(bundle.getString("cateIdTo"), "positive");
            Category findCate = findById((id + ""));
            if (findCate != null) {
                cate.setCateName(InputHelper.inputString(bundle.getString("cateName") + " ( " + findCate.getCateName() + " )", "required", "consists:6-30"));

                do {
                    String parentID = InputHelper.inputString(bundle.getString("cateParen") + " ( " + findCate.getParentID() + " )");
                    try {
                        if (Integer.parseInt(parentID) >= 0) {
                            if (findById((parentID + "")) != null || Integer.parseInt(parentID) == 0) {
                                cate.setParentID(Integer.parseInt(parentID));
                                break;
                            } else {
                                System.out.println(bundle.getString("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError")));
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("inputError"));
                    }
                }
                while (true);

                if (updateAll(id + "", cate)) {
                    readWriteFile.writeFile(JCConstant.PATH_C, getAll());
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateCateSuccess"));
                } else {
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateCateError"));
                }
                break;
            } else {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("cateNull"));
            }
        }
        while (true);
    }

    public void remove() {
        int id = InputHelper.inputInt(bundle.getString("cateIdTo"));
        if (findById((id + "")) != null) {
            if (remove(id + "")) {
                readWriteFile.writeFile(JCConstant.PATH_C, getAll());
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateCateSuccess"));
            } else {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("updateCateError"));
            }
        } else {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t  " + bundle.getString("cateNull"));
        }
    }

    public void displayAll() {
        listCateLv3 = new ArrayList<>();
        System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  %s\n", bundle.getString("showCate"));
        System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t+-----------------------------------------------------------+\n");
        int i = 0, j = 0, k = 0;
        for (Category item : getAll()) {
            ++i;
            System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t|\t\t\t%d. %-43s \t|\n", i, item.getCateName());
            for (Category ite : getByParent(item.getId())) {
                ++j;
                System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t|\t\t\t\t\t%d.%d  %-33s \t|\n", i, j, ite.getCateName());
                for (Category it : getByParent(ite.getId())) {
                    ++k;
                    System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t|\t\t\t\t\t\t\t%d.%d.%d  %-23s \t|\n", i, j, k, it.getCateName());
                    listCateLv3.add(it.getId() + "");
                }
                k = 0;
            }
            j = 0;
        }
        System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t+-----------------------------------------------------------+\n");
    }

    public void detailCate() {
        int cateId = InputHelper.inputInt(bundle.getString("cateIdTo"), "positive");
        Category findCate = findById((cateId + ""));

        if (findCate != null) {
            int j = 0, k = 0;
            System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  %s\n", bundle.getString("displayDetailCate"));
            System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t+-----------------------------------------------------------+\n");
            System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t|\t\t\t%d. %-43s \t|\n", 1, findCate.getCateName());
            for (Category ite : getByParent(findCate.getId())) {
                ++j;
                System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t|\t\t\t\t\t%d.%d  %-33s \t|\n", 1, j, ite.getCateName());
                for (Category it : getByParent(ite.getId())) {
                    ++k;
                    System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t|\t\t\t\t\t\t\t%d.%d.%d  %-23s \t|\n", 1, j, k, it.getCateName());
                }
                k = 0;
            }
            j = 0;
            System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t+-----------------------------------------------------------+\n");
        } else {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + bundle.getString("cateNull"));
        }
    }

    public void findByName() {
        String name = InputHelper.inputString(bundle.getString("findByName"), "required");
        List<Category> list = findByName(name);
        System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  %s\n", bundle.getString("showCate"));
        System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t+-----------------------------------------------------------+\n");
        int i = 0, j = 0, k = 0;
        if (list.size() != 0) {
            for (Category item : list) {
                ++i;
                System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t|\t\t\t\t\t\t%d. %-33s|\n", i, item.getCateName());
            }
        } else {
            System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t|\t\t\t   [ - Không Có Kết Quả Phù Hợp - ]  \t\t\t|\n");
        }
        System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t+-----------------------------------------------------------+\n");
    }

}
