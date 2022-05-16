# Code generation with generate-it

## Regenerating the spring project

In order to regenrate the codebase, copy the `"generate-it"` and `"gen"` scripts from the [nodegen package.json](./package.json) script, as well as the [ignore-tpl-tag.js](./ignore-tpl-tag.js) file to the location you are generating from.

eg:
```json
{
  "name": "acr-spring-project",
  "version": "1.0.0",
  "description": "Some spring project",
  "scripts": {
    "generate-it": "node -r ./ignore-tpl-tag.js node_modules/.bin/generate-it",
    "gen": "npm run generate-it -- -y -m -t https://github.com/acr-lfr/generate-it-java-spring-server ./api.yml"
  },
  "author": "me"
}
```

This will prevent the spring template from having to update it's tags every time generate-it does.

## Creating or updating generate-it template helper files

You can add custom template helpers by updating and building this typescript project.

When updating or adding new helpers, modifications to the typescript files in the [src](./src) folder need to be built by running:
```bash
npm run build
```

This will output javascript [template helpers](./helpers) so that generate-it can use them.
