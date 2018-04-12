<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:message var="matchedPasswordsMsg" key="MatchedPasswords.user.password" />
<fmt:message var="unmatchedPasswordsMsg" key="UnmatchedPasswords.user.password" />

<script type="text/javascript">

    (function () {
        $(document).ready(onLoad);

        var p1, p2;

        function onLoad() {
            $("#password").keyup(passwordsKeyUp);
            $("#confirmPassword").keyup(passwordsKeyUp);
            $("#accountDetails").submit(canSubmit);
        }

        function passwordsKeyUp() {

            p1 = $("#password").val();
            p2 = $("#confirmPassword").val();

            if (p1.length > 3 || p2.length > 3) {
                if (p1 != p2) {
                    //alert(p1 + ": " + p2);

                    //$("#passwordValidationMsg").removeClass("passwordsmatch");
                    $("#passwordValidationMsg").addClass("passwordsdonotmatch");
                    $("#passwordValidationMsg").text("${unmatchedPasswordsMsg}");
                } else {

                    $("#passwordValidationMsg").removeClass("passwordsdonotmatch");
                    $("#passwordValidationMsg").addClass("passwordsmatch");
                    $("#passwordValidationMsg").text("${matchedPasswordsMsg}");
                }
            }
        }

        function canSubmit() {
            if (p1 != p2) {
                alert("The passwords do not match...");
                return false;
            }
            return true;
        }
    })();

</script>