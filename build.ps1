Get-Content .env.local | ForEach-Object {
    if ($_ -match "^([^#=]+)=(.*)$") {
        $name = $matches[1]
        $value = $matches[2]
        Set-Item -Path "env:$name" -Value $value
    }
}

$env:PATH = "$env:JAVA_HOME\bin;$env:MAVEN_HOME\bin;$env:PATH"

& "$env:MAVEN_HOME\bin\mvn.cmd" clean package