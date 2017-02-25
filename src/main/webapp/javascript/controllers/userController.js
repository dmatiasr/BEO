angular.module('app')
.controller('UserController', function ($scope,$http,$location,$window) {

  $scope.add = function (formData) {
    alert(formData.name);
    $http.post("http://localhost:4567/users",
      {
        name:formData.name,
        address: formData.address
      }
  ).success(function (data,status) {
    alert(status)
    if(status==200){
      $location.path("/");
      $window.location.reload();
    }
  }).error(function (data,status) {
    $scope.msg="No se guardo"
    });
  }

})
