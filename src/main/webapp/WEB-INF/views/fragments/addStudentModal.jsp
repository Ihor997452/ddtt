<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="addStudentModal" tabindex="-1" aria-labelledby="addStudentLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="addStudentLabel">
          Add Student
        </h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="${pageContext.request.contextPath}/teacher/addStudent" method="post" id="addStudentForm"
              class="d-flex flex-column">

          <div class="form-group">
            <label for="student-email">
              Student Email:
            </label>
            <input required type="email" class="form-control"
                   name="student-email" id="student-email">
          </div>

        </form>

        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
            Cancel
          </button>
          <button type="submit" form="addStudentForm" class="btn btn-dark">
            Create
          </button>
        </div>
      </div>
    </div>
  </div>
</div>

