package com.dakuris.foosbar.controller;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.dakuris.foosbar.base.GameCSVBean;
import com.dakuris.foosbar.manager.FileUploadManager;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashi.dakuri@solutionset.com
 * Date: 1/25/14
 * Time: 10:13 PM
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    FileUploadManager uploadManager;

    @RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data")
    public @ResponseBody void handleFileUpload(HttpServletRequest req){
        ServletFileUpload upload = new ServletFileUpload();

        try {
            FileItemIterator iterator = upload.getItemIterator(req);
            while(iterator.hasNext()){
                FileItemStream item = iterator.next();
                InputStream in = item.openStream();
                CSVReader reader = new CSVReader(new InputStreamReader(in));
                ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
                strat.setType(GameCSVBean.class);

                String[] columns = new String[]{"firstPlayerName","firstPlayerScore","secondPlayerName","secondPlayerScore"};
                strat.setColumnMapping(columns);

                CsvToBean<GameCSVBean> csv = new CsvToBean<GameCSVBean>();
                reader.readNext(); //Ignore the header

                List<GameCSVBean> listOfGames = csv.parse(strat,reader);

                uploadManager.parseUploadedResults(listOfGames);

            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


            /*if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    BufferedOutputStream stream =
                            new BufferedOutputStream(new FileOutputStream(new File("temp.txt")));
                    stream.write(bytes);
                    stream.close();
                    return "You successfully uploaded !";
                } catch (Exception e) {
                    return "You failed to upload  => " + e.getMessage();
                }
            } else {
                return "You failed to upload because the file was empty.";
            }*/
        }
}
