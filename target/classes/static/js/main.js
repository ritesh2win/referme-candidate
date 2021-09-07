$(function(){
    $("#form-register").validate({
        rules: {
            password : {
                required : true,
            },
            confirm_password: {
                equalTo: "#password"
            }
        },
        messages: {
            username: {
                required: "Please provide an username"
            },
            email: {
                required: "Please provide an email"
            },
            password: {
                required: "Please provide a password"
            },
            confirm_password: {
                required: "Please provide a password",
                equalTo: "Please enter the same password"
            }
        }
    });
    $("#form-total").steps({
        headerTag: "h2",
        bodyTag: "section",
        transitionEffect: "fade",
        // enableAllSteps: true,
        autoFocus: true,
        transitionEffectSpeed: 500,
        titleTemplate : '<div class="title">#title#</div>',
        labels: {
            previous : 'Back',
            next : '<i class="zmdi zmdi-arrow-right"></i>',
            finish : '<i class="zmdi zmdi-arrow-right"></i>',
            current : ''
        },
        onStepChanging: function (event, currentIndex, newIndex) { 
            var firstName = $('#firstName').val();
            var lastName = $('#lastName').val();
            var email = $('#email').val();
            var contactNo = $('#contactNo').val();
            var jobID = $('#jobID').val();
            var experience = $('#experience').val();
            var skills = $('#skills').val();
            var relocate = $('#relocate').val();
            var activeForJob = $('#activeForJob').val();

            $('#firstName-val').text(firstName);
            $('#lastName-val').text(lastName);
            $('#email-val').text(email);
            $('#contactNo-val').text(contactNo);
            $('#jobID-val').text(jobID);
            $('#experience-val').text(experience);
            $('#skills-val').text(skills);
            $('#relocate-val').text(relocate);
            $('#activeForJob-val').text(activeForJob);
            $("#form-register").validate().settings.ignore = ":disabled,:hidden";
            return $("#form-register").valid();
        }
    });
});
