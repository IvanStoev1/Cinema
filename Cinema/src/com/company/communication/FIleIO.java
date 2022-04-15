package com.company.communication;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class FIleIO<T>implements Serializable{
    public void insert(T data) {
        List<T> allDataPoints = load();
        allDataPoints.add(data);
        overwrite(allDataPoints);
    }

    protected abstract String getFileName();

    public List<T> findAll() {
        return load();
    }

    private void overwrite(List<T> data) {
        try (ObjectOutputStream os =
                     new ObjectOutputStream(new FileOutputStream(getFileName()))) {
            os.writeObject(data);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
//Fix NEEDED
    private List<T> load() {
        List<T> data = new ArrayList<>();
        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(getFileName()))) {
            Object object = inputStream.readObject();
            data = (List<T>) object;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while reading from file");
        }

        return data;
    }

    protected abstract T getObject(String object);

}


