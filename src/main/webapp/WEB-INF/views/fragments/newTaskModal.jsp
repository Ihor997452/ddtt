<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="newTaskModal" tabindex="-1" aria-labelledby="topUpLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="topUpLabel">
          New Class
        </h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="${pageContext.request.contextPath}/teacher/newTask" method="post" id="newTaskForm"
              class="d-flex flex-column" onsubmit="return validateNewTaskForm()">

          <div class="form-group">
            <label for="task-name">
              Task Name:
            </label>
            <input required type="text" class="form-control"
                   name="task-name" id="task-name">
          </div>

          <div class="form-group">
            <label for="task-desc">
              Task Description:
            </label>
            <textarea required class="form-control" id="task-desc" name="task-desc"></textarea>
          </div>
        </form>

        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
            Cancel
          </button>
          <button type="submit" form="newTaskForm" class="btn btn-dark">
            Create
          </button>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath}/resources/script/validate.js"></script>
<script>
  function validateNewTaskForm() {
    return validateDescription(document.getElementById("task-desc").textLength);
  }
</script>

