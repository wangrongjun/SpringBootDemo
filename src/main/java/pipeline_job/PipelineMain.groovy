def mainVersion = "$main_version"

stage 'build pipeline-a'
node {
    echo "mainVersion: $mainVersion"
    def job = build job: "pipeline-a", parameters: [
            [$class: "StringParameterValue", name: "a_version", defaultValue: '2.0', value: mainVersion, description: 'a version'],
    ]
}

stage 'confirm'
timeout(time: 1, unit: 'DAYS') {
    input message: 'Approval to build pipeline-b?', submitter: 'rong'
}

stage 'build pipeline-b'
node {
    def job = build job: "pipeline-b", parameters: [
            [$class: "StringParameterValue", name: "b_version", defaultValue: '3.0', value: mainVersion, description: 'b version'],
    ]
}
