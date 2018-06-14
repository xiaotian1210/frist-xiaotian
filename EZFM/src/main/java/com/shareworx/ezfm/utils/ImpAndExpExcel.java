package com.shareworx.ezfm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.shareworx.platform.exception.ShareworxRuntimeException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.util.DateTimeUtil;

public class ImpAndExpExcel {

	/**
	 * 导入Excel 依据上传文件返还JSON数组对象，JSON属性为heads
	 *
	 * @param file
	 *            导入的文件
	 * @param heads
	 *            定义对象的列名
	 * @param rowStartIndex
	 *            从第几行开始读取
	 * @return
	 * @throws IOException
	 */
	public static JSONArray doImpExcel(MultipartFile file, String[] fields, Integer docReadStartRowIndex,String title)
			throws IOException {


		POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
		HSSFWorkbook wb = new HSSFWorkbook(fs);

		JSONArray jsonArray = new JSONArray();
		int startRowIndex = (null == docReadStartRowIndex ? 2 : docReadStartRowIndex);

		HSSFSheet sheet = wb.getSheetAt(0);// 只导入sheet第一页
		HSSFCell title_cell = getCell(sheet, 0, 0, true);
		String title_value = getCellStringValue(title_cell);
		if(!title_value.equals(title)){
			throw new ShareworxRuntimeException("导入的文件不对");
		}
		// 遍历所有行记录，sheet.getLastRowNum()获取的是最后一行的index
		for (int startRow = startRowIndex; startRow <= sheet.getLastRowNum(); startRow++) {
			// 遍历记录所有列
			JSONObject jsonObj = new JSONObject();
			for (int columnIndex = 0; columnIndex < fields.length; columnIndex++) {
				HSSFCell nowCell = getCell(sheet, startRow, columnIndex, true);
				String value = getCellStringValue(nowCell);
				if (null != value) {
					value = value.trim();
				}
				jsonObj.put(fields[columnIndex], value);
			}
			jsonArray.add(jsonObj);
		}
		return jsonArray;
	}


	/**
	 * 导入Excel 依据上传文件返还JSON数组对象，JSON属性为heads
	 * 
	 * @param file
	 *            导入的文件
	 * @param heads
	 *            定义对象的列名
	 * @param rowStartIndex
	 *            从第几行开始读取
	 * @return
	 * @throws IOException
	 */
	public static JSONArray doImpExcel(MultipartFile file, String[] fields, Integer docReadStartRowIndex)
			throws IOException {
		POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
		HSSFWorkbook wb = new HSSFWorkbook(fs);

		JSONArray jsonArray = new JSONArray();
		int startRowIndex = (null == docReadStartRowIndex ? 2 : docReadStartRowIndex);

		HSSFSheet sheet = wb.getSheetAt(0);// 只导入sheet第一页

		// 遍历所有行记录，sheet.getLastRowNum()获取的是最后一行的index
		for (int startRow = startRowIndex; startRow <= sheet.getLastRowNum(); startRow++) {
			// 遍历记录所有列
			JSONObject jsonObj = new JSONObject();
			for (int columnIndex = 0; columnIndex < fields.length; columnIndex++) {
				HSSFCell nowCell = getCell(sheet, startRow, columnIndex, true);
				String value = getCellStringValue(nowCell);
				if (null != value) {
					value = value.trim();
				}
				jsonObj.put(fields[columnIndex], value);
			}
			jsonArray.add(jsonObj);
		}
		return jsonArray;
	}

	/**
	 * 导出Excel
	 * 
	 * @param object//导出对象，可以是数组可以是对象
	 * @param fields//要导出对象的所需要的属性，注意跟模板书序一直
	 * @param docTemplatePath
	 *            如
	 *            1：/D:/saw_workspace/property/property-manage-ui/target/classes
	 *            //templates/supplier/设施设备管理导入模板.xls
	 *            2：/templates/supplier/供应商管理导入模板.xls docTemplatePath =
	 *            this.getClass().getResource("/").getPath()+/templates/supplier
	 *            /供应商管理导入模板.xls";
	 * @param docWriteStartRowIndex//从模板第几行开始写入
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static ModelAndResult doExpExcel(Object object, String[] fields, String docTemplatePath,
			Integer docWriteStartRowIndex) {
		docTemplatePath = docTemplatePath.replaceAll("\\\\", "/");
		String projectPath = ImpAndExpExcel.class.getResource("/").getPath().replaceAll("\\\\", "/");
		if (!docTemplatePath.contains(projectPath)) {
			docTemplatePath = projectPath + "/" + docTemplatePath;
		}
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(docTemplatePath));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return new ModelAndResult(false, e1.getLocalizedMessage());
		}
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return new ModelAndResult(false, e1.getLocalizedMessage());
		}
		HSSFSheet sheet = workbook.getSheetAt(0);

		int startRowIndex = (null == docWriteStartRowIndex ? 2 : docWriteStartRowIndex);

		JSONArray jsonArr = (JSONArray) JSONArray.toJSON(object);
		for (int i = 0; i < jsonArr.size(); i++, startRowIndex++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			for (int colIndex = 0; colIndex < fields.length; colIndex++) {
				HSSFCell tempCell = getCell(sheet, startRowIndex, colIndex, true);

				tempCell.setCellValue(jsonObj.getString(fields[colIndex]));
			}
		}

		ServletOutputStream out = null;
		String excName = docTemplatePath.substring(docTemplatePath.lastIndexOf("/") + 1);
		try {
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.addHeader("Content-Disposition",
					"attachment; filename=\"" + new String(excName.getBytes("GB2312"), "ISO8859-1") + "\";");//
			out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			return new ModelAndResult();
		} catch (IOException e) {
			e.printStackTrace();
			return new ModelAndResult(false, e.getLocalizedMessage());
		} finally {
			try {
				out.close();
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取导出Excel的workbook
	 * 
	 * @param object//导出对象，可以是数组可以是对象
	 * @param fields//要导出对象的所需要的属性，注意跟模板书序一直
	 * @param docTemplatePath
	 *            如
	 *            1：/D:/saw_workspace/property/property-manage-ui/target/classes
	 *            //templates/supplier/设施设备管理导入模板.xls
	 *            2：/templates/supplier/供应商管理导入模板.xls docTemplatePath =
	 *            this.getClass().getResource("/").getPath()+/templates/supplier
	 *            /供应商管理导入模板.xls";
	 * @param docWriteStartRowIndex//从模板第几行开始写入
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static HSSFWorkbook getHSSFWorkbook(Object object, String[] fields, String docTemplatePath,
			Integer docWriteStartRowIndex) throws FileNotFoundException, IOException {
		docTemplatePath = docTemplatePath.replaceAll("\\\\", "/");
		String projectPath = ImpAndExpExcel.class.getResource("/").getPath().replaceAll("\\\\", "/");
		if (!docTemplatePath.contains(projectPath)) {
			docTemplatePath = projectPath + "/" + docTemplatePath;
		}
		FileInputStream inputStream = new FileInputStream(new File(docTemplatePath));
		HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));
		HSSFSheet sheet = workbook.getSheetAt(0);

		int startRowIndex = (null == docWriteStartRowIndex ? 2 : docWriteStartRowIndex);

		JSONArray jsonArr = (JSONArray) JSONArray.toJSON(object);
		for (int i = 0; i < jsonArr.size(); i++, startRowIndex++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			for (int colIndex = 0; colIndex < fields.length; colIndex++) {
				HSSFCell tempCell = getCell(sheet, startRowIndex, colIndex, true);

				tempCell.setCellValue(jsonObj.getString(fields[colIndex]));
			}
		}

		return workbook;
	}

	/**
	 * 此方法用于下载指定文件。
	 * 
	 * @param response
	 *            用于防止下载乱码，设置输出流的相关信息
	 * @param filePath
	 *            如
	 *            1：/D:/saw_workspace/property/property-manage-ui/target/classes
	 *            //templates/supplier/设施设备管理导入模板.xls
	 *            2：/templates/supplier/供应商管理导入模板.xls filePath =
	 *            this.getClass().getResource("/").getPath()+/templates/supplier
	 *            /供应商管理导入模板.xls";
	 * @return true 下载成功， false 下载失败
	 */
	public static ModelAndResult download(String filePath) {
		ServletOutputStream out = null;
		FileInputStream inputStream = null;
		filePath = filePath.replaceAll("\\\\", "/");
		String projectPath = ImpAndExpExcel.class.getResource("/").getPath().replaceAll("\\\\", "/");
		if (!filePath.contains(projectPath)) {
			filePath = projectPath + "/" + filePath;
		}
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
		try {
			inputStream = new FileInputStream(new File(filePath));
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.addHeader("Content-Disposition",
					"attachment; filename=\"" + new String(fileName.getBytes("GB2312"), "ISO8859-1") + "\";");//

			out = response.getOutputStream();

			int b = 0;
			byte[] buffer = new byte[512];
			while ((b = inputStream.read(buffer)) != -1) {
				out.write(buffer, 0, b);
			}

			out.flush();
			return new ModelAndResult();
		} catch (IOException e) {
			e.printStackTrace();
			return new ModelAndResult(false, e.getLocalizedMessage());
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 获取单元格，不存在是否创建
	public static HSSFCell getCell(HSSFSheet sheet, int rowIndex, int colIndex, boolean flag) {
		if (flag) {
			HSSFRow row = sheet.getRow(rowIndex);
			if (row == null) {
				row = sheet.createRow(rowIndex);
				row.setHeightInPoints(24);// 设置行的高度（单元格的高度）
			}
			HSSFCell cell = row.getCell(colIndex);
			if (cell == null) {
				cell = row.createCell(colIndex);
			}
			return cell;
		} else {
			return getCell(sheet, rowIndex, colIndex);
		}
	}

	// 获取单元格
	public static HSSFCell getCell(HSSFSheet sheet, int rowIndex, int colIndex) {
		HSSFRow row = sheet.getRow(rowIndex);
		if (row != null) {
			HSSFCell cell = row.getCell(colIndex);
			if (cell != null) {
				return cell;
			}
		}
		return null;
	}

	// 获取Cell里面的值
	// 因为cell单元格有格式，所以针对不同的格式取值
	public static String getCellStringValue(HSSFCell cell) {
		String cellValue = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:// 字符串类型
			cellValue = cell.getStringCellValue();
			if (cellValue.trim().equals("") || cellValue.trim().length() <= 0)
				cellValue = " ";
			break;
		case HSSFCell.CELL_TYPE_NUMERIC: // 数值类型
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date d = cell.getDateCellValue();
				if (d != null) {
					cellValue = DateTimeUtil.getDate(d, DateTimeUtil.SHORTFORMAT);
				} else {
					cellValue = "";
				}
			} else {
				cellValue = cell.getNumericCellValue() + "";
				cellValue = cellValue.substring(0, cellValue.indexOf("."));
			}
			break;
		case HSSFCell.CELL_TYPE_FORMULA: // 公式
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			cellValue = " ";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			break;
		default:
			break;
		}
		return cellValue;
	}
}
