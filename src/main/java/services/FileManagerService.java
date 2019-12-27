package services;

import config.ContentConfig;
import config.TorrentConfig;
import dto.filemanager.TreeGenEnum;
import dto.filemanager.treegen.TreeGenNode;
import dto.filemanager.treegen.TreeGenNodeData;
import entities.content.HvssContent;
import entities.content.HvssContentElement;
import entities.content.HvssContentGroup;
import entities.file.HvssFileLocation;
import entities.file.HvssFileLocationTypeEnum;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class FileManagerService {

    Map<String, String> filePathIndex = new HashMap<>();

    @Autowired
    TorrentConfig torrentConfig;

    @Autowired
    ContentConfig contentConfig;

    public void addFileIndex(){

    }

    //Contentification is only needed when we work with a torrent BATCH type
    // This means we only need Content Object and NOT ContentElement
    public void torrentContentContentify(HvssContentGroup contentGroup){
        File f = new File(contentGroup.getTorrentData().getDownloadedContentSavePath());
        List<String> files = Arrays.asList(Objects.<String[]>requireNonNull(f.list()));
    }

    //Contentification is only needed when we work with a torrent BATCH type
    // This means we only need Content Object and NOT ContentElement
    public void torrentContentContentify(HvssContent content){
        TreeGenNode transformedTorrentFileTree = genTree(content.getTorrentData().getDownloadedContentSavePath());
        HvssContent hvssContent = new HvssContent();
        hvssContent.setUid(UUID.randomUUID().toString());
        hvssContent.setPrimaryLocation(HvssFileLocationTypeEnum.STORAGE);
        hvssContent.setContentElement(new ArrayList<>());
        for (TreeGenNode node: transformedTorrentFileTree.getElementList()) {
            HvssContentElement contentElement = new HvssContentElement();
            contentElement.setUid(UUID.randomUUID().toString());
            contentElement.setFileLocation(new ArrayList<>());
            HvssFileLocation fileLocation = new HvssFileLocation();
            fileLocation.setUid(UUID.randomUUID().toString());
            fileLocation.setFileLocationType(HvssFileLocationTypeEnum.STORAGE);
            fileLocation.setFilePath(node.getData().getVideoPath());
            fileLocation.setSubtitlePath(node.getData().getSubtitlePath());
            contentElement.getFileLocation().add(fileLocation);
            hvssContent.getContentElement().add(contentElement);
        }
    }


    public TreeGenNode genTree(String rootPath) {
        TreeGenNode treeGenNode = new TreeGenNode();

        boolean levelHasDir = false;
        boolean levelElementLevel = false;
        boolean levelContentLevel = false;
        boolean levelGroupLevel = false;
        File f = new File(rootPath);
        List<String> files = Arrays.asList(Objects.<String[]>requireNonNull(f.list()));

        //precheck
        for (String entry : files) {
            File entryFile = new File(rootPath + File.separator + entry);
            if (entryFile.isDirectory()) {
                levelHasDir = true;
            }
            if (entryFile.isFile() && entry.matches(contentConfig.getVideoFormatRegex())) {
                levelElementLevel = true;
               // treeGenNode.setNodeType(TreeGenEnum.ELEMENT);
            }
        }
        if (!levelHasDir && !levelElementLevel) {
            return null;
        }
        if (levelHasDir && !levelElementLevel) {
            treeGenNode.setNodeType(TreeGenEnum.GROUP);
            for (String entry : files) {
                File entryFile = new File(rootPath + File.separator + entry);
                if (entryFile.isDirectory()) {
                    TreeGenNode treeGenNodeGroup = genTree(rootPath + File.separator + entry);
                    if(treeGenNodeGroup != null && !treeGenNodeGroup.getElementList().isEmpty()) {
                        treeGenNode.getElementList().add(genTree(rootPath + File.separator + entry));
                    }
                }
            }
        }
        for (String entry : files) {
            File entryFile = new File(rootPath + File.separator + entry);
            if (entryFile.isDirectory()) {
                TreeGenNode lowerLevelTreeGenNode = genTree(rootPath + File.separator + entry);
                if (lowerLevelTreeGenNode == null) {
                    // dead end, nothing to do
                }
            } else if (entryFile.isFile() && entry.matches(contentConfig.getVideoFormatRegex())) {
                TreeGenNodeData treeGenNodeData = new TreeGenNodeData();
                treeGenNode.setNodeType(TreeGenEnum.CONTENT);
                treeGenNode.setGroupLevelName(entryFile.getParentFile().getName());
                TreeGenNode treeGenNodeElement = new TreeGenNode();
                treeGenNodeElement.setNodeType(TreeGenEnum.ELEMENT);
                treeGenNodeData.setVideoPath(rootPath + File.separator + entry);
                treeGenNodeElement.setGroupLevelName(FilenameUtils.getBaseName(entry));
                for (String innerEntry : files) {
                    File innerEntryFile = new File(rootPath + File.separator + innerEntry);
                    if (innerEntryFile.isFile() && FilenameUtils.getBaseName(innerEntry).equals(FilenameUtils.getBaseName(entry))) {
                        if (contentConfig.getSubtitleExtensions().contains("." + FilenameUtils.getExtension(innerEntry))) {
                            treeGenNodeData.setBaseName(FilenameUtils.getBaseName(innerEntry));
                            treeGenNodeData.setSubtitlePath(rootPath + File.separator + innerEntry);

                        }
                    }
                }
                treeGenNodeElement.setData(treeGenNodeData);
                treeGenNode.getElementList().add(treeGenNodeElement);
            }
        }
        if(treeGenNode.getData() == null && treeGenNode.getNodeType() == TreeGenEnum.ELEMENT) {
            return null;
        } else {
            return treeGenNode;
        }
    }
}
