stage 'build'
node {
    echo '--------------- start build ----------------'
    git 'https://github.com/wangrongjun/SpringBootDemo.git'
    bat 'mvn clean compile'
}
stage 'test'
node {
    echo '--------------- start test -----------------'
    bat 'mvn test'
}
stage 'deploy'
node {
    input 'Are you sure deploy my project?'
    bat 'mvn install'
}
