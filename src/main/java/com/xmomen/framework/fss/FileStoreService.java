package com.xmomen.framework.fss;

import java.io.IOException;
import java.io.InputStream;


public interface FileStoreService {

    /**
     * 是否存在文件
     * @param filePath
     * @return
     */
    public boolean existFile(String filePath);

    /**
     * 根据路径获取文件流
     * @param key
     * @return
     * @throws IOException
     */
    public InputStream getFile(String key) throws IOException;

    /**
     *
     * @param filePathAndName
     *            String 文件路径及名称 如c:/fqf.txt
     * @param inputStream
     *            String 文件内容
     * @throws IOException
     */
    public void newFile(String filePathAndName, InputStream inputStream) throws IOException;

    /**
     * 删除文件
     * @param filePathAndName
     */
    public void deleteFile(String filePathAndName);

    /**
     * 移动文件
     * @param srcFileName    源文件完整路径
     * @param destDirName    目的目录完整路径
     * @return 文件移动成功返回true，否则返回false
     */
    public boolean moveFile(String srcFileName, String destDirName);

    /**
     * 剪切并覆盖某个文件
     *
     * @param srcFileName
     *            待复制的文件名
     * @param destFileName
     *            目标文件名
     * @param overlay
     *            如果目标文件存在，是否覆盖
     * @return 如果复制成功，则返回true，否则返回false
     */
    public boolean copyFile(String srcFileName, String destFileName, boolean overlay);

}
