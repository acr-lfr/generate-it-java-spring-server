export const toDto = (name: string): string => (!name || name.includes('.') ? name : `com.acrontum.template.dtos.${name}Dto`);

export default toDto;
