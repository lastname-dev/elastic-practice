package com.practice.elasticsearch.excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CSVReader {

	@Value(value= "${file.path}")
	private String FILE_PATH;

	public void readCSV() {
		try {
			File file = new File(FILE_PATH);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "EUC-KR"));
			String line;
			while ((line = br.readLine())!=null) {
				List<String> aLine = new ArrayList<String>();
				String[] lineArr = line.split(",");
				aLine = Arrays.asList(lineArr);
				if(!aLine.get(8).equals("폐업")) {
					System.out.println(aLine);
				}
			}
			br.close();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
