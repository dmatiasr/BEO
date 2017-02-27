angular.module('app')
.controller('UserController', function ($scope,$http,$location,$window) {

  load_users = function() {
    console.log('7');
    $http.get("http://localhost:4567/users").then(function callback(res) {

      if (res.status==200){
        $scope.users=res.data;
        console.log(res.data);
      }else{
        alert("load_users : ops error!");
      }
    });
  }

  load_users();
  console.log($scope);


  $scope.delete= function (toDelete) {
    $http.delete("http://localhost:4567/users/deletemethod"+"?name="+toDelete.name+"&address="+toDelete.address)
      .then( function (res){
            alert(res.status);
            if (res.status==201 ){
              $location.path('/');
              $window.location.reload();
            }
      },function (res) {
            alert(res.data)
            alert("status "+res.status)
            $scope.msg="Usuario no se ha podido eliminar";
        });
  }

  $scope.add = function (formData) {
    alert(formData.name);
    $http.post("http://localhost:4567/users",
      {
        name:formData.name,
        address: formData.address
      }
  ).success(function (data,status) {
    alert(status)
    if(status==201){
      $location.path("/");
      $window.location.reload();
    }
  }).error(function (data,status) {
    $scope.msg="No se guardo"
    });
  }


})
