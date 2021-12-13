package com.bkap.util;

import com.bkap.constant.JCConstant;
import com.bkap.util.base.IBaseReadWrite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFile<O> implements IBaseReadWrite<O> {
    @Override
    public void writeFile(String path, List<O> list) {
        File file = new File(path);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(list);

            fos.close();
            oos.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<O> readFile(String path) {
        List<O> listData = new ArrayList<>();
        File file = new File(path);

        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);

                listData = (List<O>) ois.readObject();

                fis.close();
                ois.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return listData;
    }
}
