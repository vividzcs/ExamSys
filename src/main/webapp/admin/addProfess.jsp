<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<jsp:include page="/head.jsp"></jsp:include>
	<body>
		            <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
		         <div id="addDepartment" class="container tab-pane active"><br>
				       <div class="col-lg-10" >
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5> 添加专业</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="addProfessForm" action="${pageContext.request.contextPath}/admin/profess_add.action" method="post">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">院系</label>
                                        <div class="col-sm-8">
                                            <select class="form-control m-b"  ng-model="selected" ng-options="m.department for m in departments" ng-change="changeClassification(selected)" name="academy.a_id">
                                                 <option value="0">-- 请选择 --</option>
                                                 <c:forEach items="${list }" var="academy">
                                                 <option value="${academy.a_id }">${academy.a_name }</option>
                                                 </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                     <div class="form-group">
                                        <label class="col-sm-3 control-label">专业编号</label>
                                        <div class="col-sm-8">
                                            <input id="professnumber" name="m_number" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error sname">
                                        </div>
                                    </div>
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">专业名称</label>
                                        <div class="col-sm-8">
                                            <input id="professname" name="m_name" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error sname">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary addStudet" type="submit"> 确认添加</button>&nbsp;&nbsp;&nbsp;<button class="btn btn-primary addStudet" type="submit" ><a href="manageProfess.html" style="color: white;">返回列表</a></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                      </div>
				    </div>
				    </div>
				   <jsp:include page="/footer.jsp"></jsp:include> 
    <script>
      $.validator.setDefaults({
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function (element) {
                element.closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            errorElement: "span",
            errorClass: "help-block m-b-none",
            validClass: "help-block m-b-none"


        });

         //以下为官方示例
        $().ready(function () {
            // validate the comment form when it is submitted
            $("#commentForm").validate();

            // validate signup form on keyup and submit
            $("#addProfessForm").validate({
                rules: {
                    professname:  "required",
                    professnumber:"required",
                    departmentname:"required"
                },
                messages: {
                  
                    professname:'输入专业名称',
                    professnumber:"输入专业编号",
                    departmentname:"输入院系名称"
                 
                } 

            });
        
        });
        </script>
	</body>
</html>
