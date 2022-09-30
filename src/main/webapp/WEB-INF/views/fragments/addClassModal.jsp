<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="addClassModal" tabindex="-1" aria-labelledby="addClassLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="addClassLabel">
          Enroll
        </h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="${pageContext.request.contextPath}/student/enroll" method="post" id="addClassForm"
              class="d-flex flex-column" onsubmit="return validateNewClassForm()">

          <div class="form-group">
            <label for="class-to-enroll">
              Class Id (given by teacher):
            </label>
            <input required type="number" class="form-control"
                   name="class-to-enroll" id="class-to-enroll">
          </div>
        </form>

        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
            Cancel
          </button>
          <button type="submit" form="addClassForm" class="btn btn-dark">
            Enroll
          </button>
        </div>
      </div>
    </div>
  </div>
</div>

