package top.zshan.ggkt.model.live;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.zshan.ggkt.model.base.BaseEntity;

@Data
@ApiModel(description = "CourseDescription")
@TableName("live_course_description")
public class LiveCourseDescription extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "课程ID")
	@TableField("live_course_id")
	private Long liveCourseId;

	@ApiModelProperty(value = "课程简介")
	@TableField("description")
	private String description;

}