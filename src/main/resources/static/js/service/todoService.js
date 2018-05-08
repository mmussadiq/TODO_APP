/**
 * Created by Mussadiq on 5/8/2018.
 */

todoApp.factory("todoService", function($http){
    return{
        saveTask : function(payload, isUpdate){
            var url;
                if(isUpdate){
                url = "http://localhost:8099/task/update";
                return $http.put(url, payload);
            }else{
                url = "http://localhost:8099/task/add";
                return $http.post(url, payload);
            }

        },
        deleteTask : function(taskId){
            const url = "http://localhost:8099/task/delete/"+taskId;
            return $http.delete(url);
        },
        listTasks : function(){
            const url = "http://localhost:8099/task/list";
            return $http.get(url);
        }
    }
});