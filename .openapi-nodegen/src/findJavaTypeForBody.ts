interface RequestDefinitions {
  body?: Body;
}

interface Path {
  'requestBody'?: Body;
  'x-request-definitions': RequestDefinitions;
}

interface Body {
  content?: Record<string, string>;
  name?: string;
  params?: {
    path?: string;
  }[];
}

/**
 * Finds the type defined on the Swagger
 * @param requestDefinitions
 * @returns {string|null}
 */
const findBySwaggerRequestDefinition = (requestDefinitions: RequestDefinitions = {}): string => {
  const { body = {} } = requestDefinitions;
  const { params = [] } = body;

  if (params.length) {
    const { path = '' } = params[0];
    if (path) {
      return path.replace('components.schemas.', '');
    }
  }

  return null;
};

/**
 * Find if the Java type for the requestBody
 * @param body
 * @returns {boolean}
 */
const isMultipart = (body: Body = {}): boolean => {
  const { content } = body;

  return Object.keys(content).some((item) => item.includes('multipart'));
};

/**
 * Finds the correct Java type
 * @param path
 * @returns {string}
 */
export const findJavaTypeForBody = (path: Path): string => {
  const { requestBody: body = {} } = path;
  if (isMultipart(body)) {
    return 'org.springframework.web.multipart.MultipartFile';
  }

  const typeByDefinition = findBySwaggerRequestDefinition(path['x-request-definitions']);
  if (typeByDefinition) {
    return typeByDefinition;
  }

  return body.name || '';
};

export default findJavaTypeForBody;
