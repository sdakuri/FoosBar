package com.dakuris.foosbar.manager;

import com.dakuris.foosbar.base.GameCSVBean;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Shashidhar Dakuri
 * Email: shashi.dakuri@solutionset.com
 * Date: 1/25/14
 * Time: 11:45 PM
 */
public interface FileUploadManager {

    public void parseUploadedResults(List<GameCSVBean> listOfGames);
}
