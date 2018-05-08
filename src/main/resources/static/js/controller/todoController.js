/**
 * Created by Mussadiq on 5/8/2018.
 */

todoApp.controller( "addTaskCtrl", function( $scope, todoService ) {

        $scope.addTask = function(){
        console.log("hello")
        debugger;
        var payload = {
            name : $scope.taskName,
            dueDate : $scope.dueDate,
            status : "TODO"
        };
        var dateRegularExp = "^[0-9]{2}-[0-9]{2}-[0-9]{4}$";
        if(!payload.dueDate.match(dateRegularExp)){
            $scope.errorMessage = "Invalid date. Valid format is dd-mm-yyyy"
            return false;
        }
        todoService.saveTask(payload, false)
            .then(function (response) {
                $scope.successMessage = "Task added successfully.";
                $scope.getAllTasks();
                $scope.resetVariables();
            }).catch(function (error) {
                console.log("error");
                $scope.errorMessage = error.data.message;
            });
    }

    $scope.updateTask = function(task){
        console.log("hello")
        debugger;
        if(task){
            //this block will called in case of done call
            var payload = {
                id : task.id,
                name : task.name,
                dueDate : task.dueDate,
                status : "COMPLETED"
            };
        }else{
            var payload = {
                id : $scope.taskId,
                name : $scope.taskName,
                dueDate : $scope.dueDate,
                status : $scope.status
            };
        }
        var dateRegularExp = "^[0-9]{2}-[0-9]{2}-[0-9]{4}$";
        if(!payload.dueDate.match(dateRegularExp)){
            $scope.errorMessage = "Invalid date. Valid format is dd-mm-yyyy"
            return false;
        }
        todoService.saveTask(payload, true)
            .then(function (response) {
                $scope.successMessage = "Task updated successfully.";
                $scope.isUpdate = false;
                $scope.getAllTasks();
                $scope.resetVariables();
            }).catch(function (error) {
            console.log("error");
            $scope.errorMessage = error.data.message;
        });
    }

    $scope.allowUpdate = function (task) {
        $scope.isUpdate = true;
        $scope.taskName = task.name;
        $scope.dueDate = task.dueDate;
        $scope.status = task.status;
        $scope.taskId = task.id;
    }
    $scope.getAllTasks = function(){
        debugger;
        todoService.listTasks()
            .then(function (response) {
                $scope.tasks = response.data;
            })
    }
    $scope.deleteTask = function(task){
        debugger;
        todoService.deleteTask(task.id)
            .then(function (response) {
                $scope.successMessage = "Task deleted successfully.";
                $scope.getAllTasks();
            }).catch(function (error) {
            console.log("error");
            $scope.errorMessage = error.data.message;
        });
    }
    $scope.resetMessages = function () {
        $scope.errorMessage = null;
        $scope.successMessage = null;
    };
    $scope.getAllTasks();

    $scope.resetVariables = function () {
        $scope.taskName = null;
        $scope.dueDate = null;
        $scope.status = null;
        $scope.taskId = null;
    };

})