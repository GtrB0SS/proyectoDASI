# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM payara/micro
COPY ./target/Database2-1.0.war ${DEPLOY_DIR}/ROOT.war
CMD ["--deploymentDir", "/opt/payara/deployments", "--deploy", "/opt/payara/deployments/ROOT.war", "--contextroot", "/"]
