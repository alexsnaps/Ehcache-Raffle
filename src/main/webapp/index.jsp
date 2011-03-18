<%@page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Ehcache @ BeJUG</title>
    <link rel="stylesheet" href="style.css" TYPE="text/css" media="screen"/>
    <style type="text/css">
       h1 { font-size: 96px}
        h1, h2 {
            color: rgb(132, 56, 37);
        }

        h2 em {
            color: black;
        }
        label.label {
            display: block;
            width: 100%;
            text-align: right;
        }

        label.label:after {
            content: " :";
        }

        #smallPrint:before {
            content: "* ";
        }
       h3 { font-size: 48px; line-height: 56px; font-weight: bold;}
    </style>
    <script>
    function changeTitle(element) {
       if(element.value == "other") {
          var field = document.getElementById('title');
          var parent = field.parentNode;
          parent.removeChild(field);
          field = document.createElement('input');
          field.name = "title";
          field.id = "title";
          parent.appendChild(field);
          field.focus();
       }
    }
    </script>
</head>
<body onload="document.forms[0].elements[0].focus();">
<img src="logo.gif" alt="logo" width="294" height="60" style="margin: 25px auto 50px auto; display: block"/> 


<form action="participate" method="post" name="participate">
    <table style="margin: 25px auto">
        <tr><td colspan="2">
<h1>To win an iPod Shuffle</h1>
<h2>(and not ruin the demo)</h2>
<h2>Just enter your information below:</h2>
</td></tr>
        <tr>
            <td><label for="firstName" class="label">First Name</label></td>
            <td><input type="text" name="firstName" id="firstName"/></td>
        </tr>
        <tr>
            <td><label for="lastName" class="label">Last Name</label></td>
            <td><input type="text" name="lastName" id="lastName"/></td>
        </tr>
        <tr>
            <td><label for="company" class="label">Company</label></td>
            <td><input type="text" name="company" id="company"/></td>
        </tr>
        <tr>
            <td><label for="title" class="label">Job Title</label></td>
            <td>
            <select name="title" id="title" onchange="changeTitle(this);">
               <option value="Software Engineer">Software Engineer</option>
               <option value="Architect">Architect</option>
               <option value="Project Manager">Project Manager</option>
               <option value="SysAdmin">System Operator / Administrator</option>
               <option value="Head of Development">Head of Development</option>
               <option value="other">Other</option>
            </select>
            </td>
        </tr>
        <tr>
            <td><label for="email" class="label">Email</label></td>
            <td><input type="text" name="email" id="email"/></td>
        </tr>
        <tr>
            <td><label for="uses" class="label">Using</label></td>
            <td>
            <select name="uses" id="uses">
               <option value="none">None</option>
               <option value="ehcache">Ehcache Open Source</option>
               <option value="ehcacheEnterprise">Ehcache Enterprise</option>
               <option value="ehcacheReplilcation">Ehcache replication</option>
               <option value="terracotta">Terracotta</option>
               <option value="other">Other</option>
            </select>
            </td>
        </tr>
        <!--tr>
            <td></td>
            <td><input type="checkbox" name="atScale" id="atScale"/> <label for="atScale">check this box, if you are able to travel to San Francisco, CA in September *</label></td>
        </tr-->
        <tr>
            <td></td>
            <td><input type="submit" value="I want to win!"/></td>
        </tr>
    </table>
</form>

<!--dl>
    <dt>* Please note :</dt>
    <dd>Only the drawing only covers the entrance pass. <br />
        Travel and accommodation are not part of the price.<br />
        @Scale conference is being held on Friday, September 24, 2010 from 8:00 AM - 4:00 PM (PST) in San Francisco, the day after JavaOne closes.
    </dd>
</dl-->
</body>
</html>
