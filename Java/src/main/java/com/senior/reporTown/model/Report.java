package com.senior.reporTown.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Document(collection = "reports")
public class Report {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId userId;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId institutionId;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId solutionId;

    private String description;
    private String category;
    private ArrayList<Comment> comments;
    @JsonSerialize(contentUsing = ToStringSerializer.class)
    private ArrayList<ObjectId> upvotes;
    private JsonObject location;
    private String report_image_link;
    private MultipartFile file;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;

    public Report(String description,
                  String category,
                  JsonObject location,
                  String report_image_link,
                  MultipartFile file,
                  ObjectId userId,
                  ObjectId institutionId) {
        this.description = description;
        this.category = category;
        comments = new ArrayList<>();
        upvotes = new ArrayList<>();
        this.location = location;
        this.userId = userId;
        this.institutionId = institutionId;
        solutionId = null;
        this.report_image_link = report_image_link;
        this.file = file;
        date = new Date();
    }
}

