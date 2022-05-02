#!/usr/bin/env node
const fs = require("fs");
const path = require("path");

const renameRepositories = () => {
    log('\nFixing repository names');
    const baseFolder = './src/main/java/com/acrontum/template/repositories';
    fs.readdirSync(baseFolder)
        .filter(file => file.endsWith('Repositorie.java'))
        .forEach(file => {
            const [fileName, extension] = file.split('.');
            const fixedName = fileName.replace('Repositorie', 'Repository');
            log('Renaming', fileName, 'to', `${fixedName}.${extension}`);
            fs.renameSync(path.join(baseFolder, file), path.join(baseFolder, `${fixedName}.${extension}`));
        });
}

const renameTestFiles = () => {
    const packages = ['./src/test/java/com/acrontum/template/controllers', './src/test/java/com/acrontum/template/services'];
    for(let currentPackage of packages) {
        log('\nAdding Test suffix to', currentPackage);
        fs.readdirSync(currentPackage)
            .filter(file => ! file.includes('Test'))
            .forEach(file => {
                const [fileName, extension] = file.split('.');
                log('Renaming', fileName, 'to', `${fileName}Test.${extension}`);
                fs.renameSync(path.join(currentPackage, file), path.join(currentPackage, `${fileName}Test.${extension}`));
            })
    }
}

const copySwaggerFile = (swaggerPath) => {
    const destination = './src/main/resources/static/swagger.yml';
    fs.copyFileSync(swaggerPath, destination);
    log('\nSwagger file copied to', destination);
}

let log = () => {};

const main = (ctx) => {
    log = ctx.verbose || ctx.veryVerbose ? console.log : log;

    log('Applying template specific changes');
    renameRepositories();
    renameTestFiles();
    copySwaggerFile(ctx.swaggerFilePath);
    log('All changes done.')
}

exports.default = main;