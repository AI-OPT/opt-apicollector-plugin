package com.ai.runner.gradle.plugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.SourceTask;
import org.gradle.api.tasks.TaskAction;

public class APICollectorTask extends SourceTask {

    private static final String OUTPUT_FILE_NAME = "apicollector.option";

    private String esconfig;

    private List<File> docletpath;

    private MemberLevel memberLevel = MemberLevel.PUBLIC;

    private String encoding = "utf-8";

    private String docletClass;

    private String belong;

    // 服务提供者
    private String owner;

    // 服务类型
    private String ownerType;

    // 服务版本
    private String version;

    // 服务坐标分组标识
    private String groupId;

    // 服务构件标识
    private String artifactId;

    @TaskAction
    public void apiCollect() throws IOException {
        validateParam();
        BufferedWriter writer = new BufferedWriter(new FileWriter(getTemporaryDir()
                + File.separator + OUTPUT_FILE_NAME));
        writer.write("-doclet" + " " + docletClass);
        writer.newLine();
        StringBuffer buffer = new StringBuffer();
        String pathSparator = System.getProperty("path.separator");
        for (File file : docletpath) {
            buffer.append(file.getAbsoluteFile() + pathSparator);
        }
        writer.write("-docletpath " + buffer);
        writer.newLine();
        writer.write("-esconfig '" + esconfig + "'");
        writer.newLine();
        writer.write(memberLevel.getlevelString());
        writer.newLine();
        writer.write("-encoding " + encoding);
        writer.newLine();
        writer.write("-owner" + " " + owner);
        writer.newLine();
        writer.write("-ownerType" + " " + ownerType);
        writer.newLine();
        writer.write("-version" + " " + version);
        writer.newLine();
        writer.write("-artifactId" + " " + artifactId);
        writer.newLine();
        writer.write("-groupId" + " " + groupId);
        writer.newLine();
        writer.write("-belong" + " " + belong);
        writer.newLine();
        for (File file : getSource()) {
            writer.write(file.getAbsolutePath());
            writer.newLine();
        }
        writer.flush();
        writer.close();

        String command = "javadoc @" + getTemporaryDir().getAbsolutePath() + File.separator
                + OUTPUT_FILE_NAME;
        CommandLine cmdLine = CommandLine.parse(command);
        DefaultExecutor executor = new DefaultExecutor();
        executor.execute(cmdLine);
    }

    private void validateParam() {
        if (docletClass == null || docletClass.length() <= 0)
            throw new IllegalArgumentException("docletClass can not be empty");
        if (owner == null || owner.length() <= 0)
            throw new IllegalArgumentException("owner can not be empty");
        if (esconfig == null || esconfig.length() <= 0)
            throw new IllegalArgumentException("esconfig can not be empty");
        if (version == null || version.length() <= 0)
            throw new IllegalArgumentException("version can not be empty");
        if (groupId == null || groupId.length() <= 0)
            throw new IllegalArgumentException("groupId can not be empty");
        if (artifactId == null || artifactId.length() <= 0)
            throw new IllegalArgumentException("artifactId can not be empty");
        if (ownerType == null || ownerType.length() <= 0)
            throw new IllegalArgumentException("ownerType can not be empty");
        if (belong == null || belong.length() <= 0)
            throw new IllegalArgumentException("belong can not be empty");
    }

    @Input
    public List<File> getDocletpath() {
        return docletpath;
    }

    public void setDocletpath(List<File> docletpath) {
        this.docletpath = docletpath;
    }

    @Input
    public String getEsconfig() {
        return esconfig;
    }

    public void setEsconfig(String esconfig) {
        this.esconfig = esconfig;
    }

    @Input
    @Optional
    public MemberLevel getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(MemberLevel memberLevel) {
        this.memberLevel = memberLevel;
    }

    @Input
    @Optional
    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Input
    public String getDocletClass() {
        return docletClass;
    }

    public void setDocletClass(String docletClass) {
        this.docletClass = docletClass;
    }

    @Input
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Input
    @Optional
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Input
    @Optional
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Input
    @Optional
    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    @Input
    @Optional
    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    @Input
    @Optional
    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

}
